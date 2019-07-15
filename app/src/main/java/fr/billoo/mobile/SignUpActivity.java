package fr.billoo.mobile;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import fr.billoo.mobile.BaseFunctionalities.APIRelatedStuff;
import fr.billoo.mobile.BaseFunctionalities.BaseCheckFunctions;
import fr.billoo.mobile.BaseFunctionalities.SessionManager;
import fr.billoo.mobile.Interfaces.BillooRetrofitCalls;
import fr.billoo.mobile.Models.SignUpModel;
import fr.billoo.mobile.Models.VerifyModel;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SignUpActivity extends AppCompatActivity {

    Button signup_button;
    EditText email_signup,password_signup,confirmpassword_signup;

    private static String SIGN_UP_URL= APIRelatedStuff.BillooBaseURL;

    private String emailString,passwordString,confirmPasswordString;

    SignUpModel signupRequest;
    VerifyModel verifyRequest;

    BillooRetrofitCalls billooRetrofitCalls;

    SessionManager sessionManager;

    private String SIGNUPLOG="SIGNUPLOG";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        email_signup = (EditText) findViewById(R.id.email_signup);
        password_signup = (EditText) findViewById(R.id.password_signup);
        confirmpassword_signup = (EditText) findViewById(R.id.confirmpassword_signup);
        signup_button = (Button)findViewById(R.id.signup_button_signup);
        sessionManager=SessionManager.getInstance(this);

        //Retro init
        Retrofit.Builder builder = new Retrofit.Builder()
                .baseUrl(SIGN_UP_URL)
                .addConverterFactory(GsonConverterFactory.create());
        Retrofit retrofit = builder.build();
        billooRetrofitCalls = retrofit.create(BillooRetrofitCalls.class);


        signup_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                if(APIRelatedStuff.isInternetAvailable(getApplicationContext())) {

                    emailString=email_signup.getText().toString();
                    passwordString=password_signup.getText().toString();
                    confirmPasswordString=confirmpassword_signup.getText().toString();

                    if(BaseCheckFunctions.isEmailValid(emailString) &&
                            BaseCheckFunctions.isPasswordValid(passwordString,confirmPasswordString)) {
                        if(!sessionManager.getIsSignUp())
                            createUser(emailString, passwordString, confirmPasswordString);
                        else
                            verificationDialog();
                    }
                    else
                    {
                        email_signup.setError(getResources().getString(R.string.error_email));
                        password_signup.setError(getResources().getString(R.string.error_password));
                        confirmpassword_signup.setError(getResources().getString(R.string.error_confirm_password));
                    }

                }
                else {
                    Toast.makeText(SignUpActivity.this, "Il y a pas le internet, mon mec.", Toast.LENGTH_SHORT).show();
                }




            }
        });
    }

    private boolean createUser(String emailString,String passwordString,String confirmPasswordString)
    {
        signupRequest = new SignUpModel(emailString,passwordString,confirmPasswordString);
        Call<SignUpModel> callSignUpRequest = billooRetrofitCalls.createUser(signupRequest.getEmail(),signupRequest.getPassword(),signupRequest.getConfirmPassword());

        callSignUpRequest.enqueue(new Callback<SignUpModel>() {
            @Override
            public void onResponse(Call<SignUpModel> call, Response<SignUpModel> response) {
                SignUpModel signUpResponse = response.body();

                if (signUpResponse != null) {
                    String responseCode = signUpResponse.getResponseCode();
                    if (responseCode != null && responseCode.equals("404")) {
                        Toast.makeText(SignUpActivity.this, "Invalid Login Details \n Please try again"+signUpResponse.getResponseCode(), Toast.LENGTH_SHORT).show();
                    }
                    else {
                        if(signUpResponse.getStatus().equals("SUCCESS")) {
                            sessionManager.createUserDetailsForSignupSession(signUpResponse.getEmail(),signUpResponse.getAuthtoken(),signUpResponse.getUserid(),signUpResponse.getBarcode());
                            verificationDialog();
                        }
                        else if(signUpResponse.getStatus().equals("FAILED"))
                            Toast.makeText(SignUpActivity.this, ""+BaseCheckFunctions.getCorrespondingCode(signUpResponse.getErrorCode()), Toast.LENGTH_SHORT).show();
                    }
                }
                else
                {
                    Log.d(SIGNUPLOG,"signUpResponse is null");
                }
            }

            @Override
            public void onFailure(Call<SignUpModel> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "onFailure called "+t.getMessage(), Toast.LENGTH_SHORT).show();
                call.cancel();
            }
        });

        return  false;
    }

    private void verificationDialog()
    {
        final AlertDialog.Builder builder = new AlertDialog.Builder(SignUpActivity.this);
        // Get the layout inflater
        final LayoutInflater inflater = SignUpActivity.this.getLayoutInflater();
        builder.setTitle("Enter the verification Code : ");

        final View v = inflater.inflate(R.layout.verification_dialog_view, null);

        // Inflate and set the layout for the dialog
        // Pass null as the parent view because its going in the dialog layout
        builder.setView(v)
                // Add action buttons
                .setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        // veriify in the user ...

                        verifyUser(sessionManager.getUserID(),sessionManager.getToken(),((EditText)v.findViewById(R.id.codeText)).getText().toString());
                    }
                })
                .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });


        Dialog d = builder.create();
        d.requestWindowFeature(Window.FEATURE_NO_TITLE);
        d.getWindow().setBackgroundDrawableResource(R.drawable.dialog_box_1);

        d.show();


        d.getWindow().setLayout(600,350);
    }


    private boolean verifyUser(final String userIDString, final String authTokenString, String codeString)
    {
        Log.d(SIGNUPLOG,"Check 1  "+userIDString + " " + authTokenString+" "+codeString);
        verifyRequest = new VerifyModel(userIDString,authTokenString,codeString);
        Log.d(SIGNUPLOG,"Check 2 "+verifyRequest.getUserid() + " " + verifyRequest.getAuthtoken()+" "+verifyRequest.getCode());
        Call<VerifyModel> callVerifyRequest = billooRetrofitCalls.verifyUser(verifyRequest.getUserid(),verifyRequest.getAuthtoken(),verifyRequest.getCode());
        //Call<VerifyModel> callVerifyRequest = billooRetrofitCalls.verifyUserTest();

        callVerifyRequest.enqueue(new Callback<VerifyModel>() {
            @Override
            public void onResponse(Call<VerifyModel> call, Response<VerifyModel> response) {
                VerifyModel verifyResponse = response.body();

                if (verifyResponse != null) {
                    String responseCode = verifyResponse.getResponseCode();
                    if (responseCode != null && responseCode.equals("404")) {
                        Toast.makeText(SignUpActivity.this, "Invalid Login Details \n Please try again"+verifyResponse.getResponseCode(), Toast.LENGTH_SHORT).show();
                    } else {
                        //sessionManager.createUserDetailsForSignupSession(signUpResponse.getEmail(),signUpResponse.getAuthtoken(),signUpResponse.getUserid(),signUpResponse.getBarcode());

                        if(verifyResponse.getStatus().equals("SUCCESS")) {
                            sessionManager.setIsVerified();
                            Log.d(SIGNUPLOG,"SESSIONCHECKVER"+sessionManager.getIsVerify());
                            sessionManager.clearStackToStartNewActivity(SignUpActivity.this,new Intent(SignUpActivity.this, HomeActivity.class));
                        }
                        else if(verifyResponse.getStatus().equals("FAILED")) {
                            Log.d(SIGNUPLOG,userIDString + " " + authTokenString);
                            Toast.makeText(SignUpActivity.this, "FALIED "+userIDString + " " + authTokenString + " " + BaseCheckFunctions.getCorrespondingCode(verifyResponse.getErrorCode()), Toast.LENGTH_SHORT).show();
                        }

                        //Toast.makeText(SignUpActivity.this, " "+sessionManager.getUserDetails(), Toast.LENGTH_SHORT).show();

                    }
                }
                else
                {
                    Toast.makeText(SignUpActivity.this, "Status 1: "+verifyResponse.getStatus() , Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<VerifyModel> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "onFailure called "+t.getMessage(), Toast.LENGTH_SHORT).show();
                call.cancel();
            }
        });

        return  false;
    }
}
