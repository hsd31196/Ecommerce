package com.example.ecommerce;


import java.util.List;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface APIInterface {

//    @POST("/login/auth/signup")
//    Call<ResponseBody> postRegistrationDetails(@Field("firstName") String userNameValue, /*@Field("lastName") String lastNameValue,*/ @Field("email") String emailValue,
//                                               @Field("password") String passwordValue/*, @Field("mobile") String mobile, @Field("address") String address*/);

    @POST("login/auth/signup")
    Call<ResponseBody> postRegistrationDetails(@Body RegistrationDTO registrationDTO);
//    @POST("/user/register")
//    Call<RegistrationDTO> postRegistrationDetails();


    @POST("login/auth/login")
    Call<ResponseBody> login(@Body LoginCheck loginCheck);
}
