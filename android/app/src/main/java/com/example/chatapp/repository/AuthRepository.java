package com.example.chatapp.repository;

import androidx.lifecycle.MutableLiveData;

import com.example.chatapp.api.AuthApi;
import com.example.chatapp.api.RetrofitClient;
import com.example.chatapp.model.User;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AuthRepository {

    private final AuthApi api = RetrofitClient.getInstance().create(AuthApi.class);

    public MutableLiveData<String> login(User user) {
        MutableLiveData<String> data = new MutableLiveData<>();

        api.login(user).enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                data.setValue(response.body());
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                data.setValue(null);
            }
        });

        return data;
    }
}