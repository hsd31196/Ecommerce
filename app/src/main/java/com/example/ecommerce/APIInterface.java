package com.example.ecommerce;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface APIInterface {

    @FormUrlEncoded
    @POST("/user/register")
    Call<ResponseBody> postRegistrationDetails(@Field("firstName") String userNameValue, @Field("lastName") String lastNameValue, @Field("email") String emailValue,
                                               @Field("password") String passwordValue, @Field("mobile") String mobile, @Field("address") String address);

}
