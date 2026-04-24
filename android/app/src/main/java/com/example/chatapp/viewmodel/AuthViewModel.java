package com.example.chatapp.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.chatapp.model.User;
import com.example.chatapp.repository.AuthRepository;

public class AuthViewModel extends ViewModel {

    private final AuthRepository repository = new AuthRepository();

    public LiveData<String> login(User user) {
        return repository.login(user);
    }
}