package fr.billoo.mobile;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import fr.billoo.mobile.BaseFunctionalities.APIRelatedStuff;
import fr.billoo.mobile.BaseFunctionalities.BaseCheckFunctions;
import fr.billoo.mobile.BaseFunctionalities.SessionManager;
import fr.billoo.mobile.Interfaces.BillooRetrofitCalls;
import fr.billoo.mobile.Models.LoginModel;
import fr.billoo.mobile.Models.SignUpModel;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LoginActivity extends AppCompatActivity
{
    TextView signUp,login;
    SessionManager sessionManager;
    LoginModel loginRequest;
    EditText emailText,passwordText;
    BillooRetrofitCalls billooRetrofitCalls;

    private static String LOG_IN_URL= APIRelatedStuff.BillooBaseURL;

    private String LOGINLOG="LOGINLOG";

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        initializeViews();

        //check if already logged in
        if(sessionManager.getIsLogin())
        {

            sessionManager.clearStackToStartNewActivity(this,new Intent(LoginActivity.this,HomeActivity.class));
            finish();
        }

        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginActivity.this,SignUpActivity.class));
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loginUser(emailText.getText().toString(),passwordText.getText().toString());
            }
        });
    }

    private boolean loginUser(String emailString,String passwordString)
    {
        loginRequest = new LoginModel(emailString,passwordString);
        Call<LoginModel> callSignUpRequest = billooRetrofitCalls.loginUser(loginRequest.getEmail(),loginRequest.getPassword());

        callSignUpRequest.enqueue(new Callback<LoginModel>() {
            @Override
            public void onResponse(Call<LoginModel> call, Response<LoginModel> response) {
                LoginModel loginResponse = response.body();

                if (loginResponse != null) {
                    String responseCode = loginResponse.getResponseCode();
                    if (responseCode != null && responseCode.equals("404")) {
                        Toast.makeText(LoginActivity.this, "Network issue"+loginResponse.getResponseCode(), Toast.LENGTH_SHORT).show();
                    }
                    else {
                        if(loginResponse.getStatus().equals("SUCCESS")) {
                            sessionManager.updateUserDetailsAfterLogin(loginResponse.getEmail(),loginResponse.getAuthtoken(),loginResponse.getUserid(),loginResponse.getBarcode());
                            sessionManager.setIsLogin();
                            sessionManager.clearStackToStartNewActivity(LoginActivity.this,new Intent(LoginActivity.this,HomeActivity.class));
                        }
                        else if(loginResponse.getStatus().equals("FAILED"))
                            Toast.makeText(LoginActivity.this, ""+ BaseCheckFunctions.getCorrespondingCode(loginResponse.getErrorCode()), Toast.LENGTH_SHORT).show();
                    }
                }
                else
                {
                    Log.d(LOGINLOG,"signUpResponse is null");
                }
            }

            @Override
            public void onFailure(Call<LoginModel> call, Throwable t) {
                Log.d(LOGINLOG,"onFailure called "+t.getMessage());
                call.cancel();
            }
        });

        return  false;
    }

    void initializeViews()
    {
        signUp=(TextView)findViewById(R.id.signup_text_login);
        login=(TextView)findViewById(R.id.button_login);

        emailText=(EditText) findViewById(R.id.email_login);
        passwordText=(EditText)findViewById(R.id.password_login);

        sessionManager = SessionManager.getInstance(this);

        //Retro init
        Retrofit.Builder builder = new Retrofit.Builder()
                .baseUrl(LOG_IN_URL)
                .addConverterFactory(GsonConverterFactory.create());
        Retrofit retrofit = builder.build();
        billooRetrofitCalls = retrofit.create(BillooRetrofitCalls.class);
    }
}
