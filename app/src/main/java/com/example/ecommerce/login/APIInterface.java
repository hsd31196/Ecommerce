package com.example.ecommerce.login;


import com.example.ecommerce.TokenClass;
import com.example.ecommerce.address.UserAddress;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;
import java.util.List;
public interface APIInterface {

//    @POST("/login/auth/signup")
//    Call<ResponseBody> postRegistrationDetails(@Field("firstName") String userNameValue, /*@Field("lastName") String lastNameValue,*/ @Field("email") String emailValue,
//                                               @Field("password") String passwordValue/*, @Field("mobile") String mobile, @Field("address") String address*/);

    @POST("login/auth/signup")
    Call<ResponseBody> postRegistrationDetails(@Body RegistrationDTO registrationDTO);
//    @POST("/user/register")
//    Call<RegistrationDTO> postRegistrationDetails();


    @POST("login/auth/signin")
    Call<TokenClass> login(@Body LoginCheck loginCheck);


    @Headers({ "Content-Type: application/json;charset=UTF-8"})
    @GET("login/user/getAddress/{userId}")
    Call<List<String>> getAddress(@Header("Authorization") String token,@Path("userId") String username);

    @Headers({ "Content-Type: application/json;charset=UTF-8"})
    @POST("login/user/setAddress")
    Call<ResponseBody> setAddress(@Header("Authorization") String token,@Body UserAddress address);

    @Headers({"Content-Type: applocation/json;charset=UTF-8"})
    @GET("login/user/loginHistory/{userEmail}")
    Call<List<LoginHistoryPOJO>> getloginHistory(@Header("Authorozation") String token, @Path("userEmail") String  userEmail);



}
