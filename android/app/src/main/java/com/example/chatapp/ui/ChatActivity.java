package com.example.chatapp.ui;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.chatapp.R;
import com.example.chatapp.network.RetrofitClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ChatActivity extends AppCompatActivity {

    RecyclerView rv;
    TextView statusText;

    Long myId = 1L;
    Long receiverId;

    @Override
    protected void onCreate(Bundle b) {
        super.onCreate(b);
        setContentView(R.layout.activity_chat);

        rv = findViewById(R.id.recyclerView);
        statusText = findViewById(R.id.statusText);

        rv.setLayoutManager(new LinearLayoutManager(this));

        receiverId = getIntent().getLongExtra("receiverId", 0);

        // 🔥 FIRST LOAD STATUS
        checkUserStatus(receiverId);

        // (chat loading already exists in your project)
    }

    // ================= ONLINE STATUS =================
    private void checkUserStatus(Long userId) {

        RetrofitClient.getChatApi()
                .getUserStatus(userId)
                .enqueue(new Callback<Boolean>() {

                    @Override
                    public void onResponse(Call<Boolean> call,
                                           Response<Boolean> response) {

                        Boolean isOnline = response.body();

                        if (isOnline != null && isOnline) {

                            statusText.setText("Online 🟢");
                            statusText.setTextColor(
                                    getResources().getColor(android.R.color.holo_green_dark)
                            );

                        } else {

                            statusText.setText("Offline 🔴");
                            statusText.setTextColor(
                                    getResources().getColor(android.R.color.holo_red_dark)
                            );
                        }
                    }

                    @Override
                    public void onFailure(Call<Boolean> call, Throwable t) {

                        statusText.setText("Offline 🔴");
                    }
                });
    }

    // ================= AUTO REFRESH =================
    @Override
    protected void onResume() {
        super.onResume();
        checkUserStatus(receiverId);
    }
}