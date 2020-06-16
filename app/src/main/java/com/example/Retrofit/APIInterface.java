package com.example.Retrofit;

import com.example.pojo.Login;
import com.example.pojo.PastPolicy;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface APIInterface {

    @POST("login")
    @FormUrlEncoded
    Call<Login> LOGIN_CALL(@Field("email") String email,
                           @Field("password") String password);

    @POST("get_past_policy")
    @FormUrlEncoded
    Call<PastPolicy> PAST_POLICY_CALL(@Field("user_id") String user_id);

}
