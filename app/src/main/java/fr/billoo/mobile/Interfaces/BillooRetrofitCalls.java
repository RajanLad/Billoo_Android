package fr.billoo.mobile.Interfaces;

import java.util.List;

import fr.billoo.mobile.Models.LoginModel;
import fr.billoo.mobile.Models.SignUpModel;
import fr.billoo.mobile.Models.VerifyModel;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.*;

public interface BillooRetrofitCalls {

    @FormUrlEncoded
    @POST("/api/rest/user/signup")
    Call<SignUpModel> createUser(@Field("email") String email,@Field("password") String password,@Field("confirmPassword") String confirmPassword);

    @FormUrlEncoded
    @POST("/api/rest/user/verify")
    Call<VerifyModel> verifyUser(@Field("userid") String userid,@Field("authtoken") String authtoken,@Field("code") String code);

    @FormUrlEncoded
    @POST("/api/rest/user/login")
    Call<LoginModel> loginUser(@Field("email") String email, @Field("password") String password);

    @GET("signup.php/")
    Call<SignUpModel> createUserTest();

    @GET("verify.php/")
    Call<VerifyModel> verifyUserTest();
}
