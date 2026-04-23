package com.example.chatapp;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;


import android.os.Bundle;
import android.widget.*;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.example.chatapp.model.User;
import com.example.chatapp.viewmodel.AuthViewModel;

public class MainActivity extends AppCompatActivity {

    EditText email, password;
    Button loginBtn;
    AuthViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        loginBtn = findViewById(R.id.loginBtn);

        viewModel = new ViewModelProvider(this).get(AuthViewModel.class);

        loginBtn.setOnClickListener(v -> {
            User user = new User(
                    email.getText().toString(),
                    password.getText().toString()
            );

            viewModel.login(user).observe(this, token -> {
                if (token != null) {
                    Toast.makeText(this, "Login Success", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(this, "Login Failed", Toast.LENGTH_SHORT).show();
                }
            });
        });
    }
}