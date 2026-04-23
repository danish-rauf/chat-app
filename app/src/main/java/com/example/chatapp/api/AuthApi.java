package com.example.chatapp.api;

import com.example.chatapp.model.User;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface AuthApi {

    @POST("auth/register")
    Call<String> register(@Body User user);

    @POST("auth/login")
    Call<String> login(@Body User user);
}