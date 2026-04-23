package com.example.chatapp.ui;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.*;

import com.example.chatapp.R;
import com.example.chatapp.adapter.ChatListAdapter;
import com.example.chatapp.model.ChatMessage;
import com.example.chatapp.network.RetrofitClient;

import java.util.List;

import retrofit2.*;

public class ChatListActivity extends AppCompatActivity {

    RecyclerView rv;
    Long myId = 1L; // TODO replace with logged user

    @Override
    protected void onCreate(Bundle b) {
        super.onCreate(b);
        setContentView(R.layout.activity_chat_list);

        rv = findViewById(R.id.recyclerView);
        rv.setLayoutManager(new LinearLayoutManager(this));

        loadChats();
    }

    void loadChats() {
        RetrofitClient.getChatApi()
                .getChatList(myId)
                .enqueue(new Callback<List<ChatMessage>>() {

                    @Override
                    public void onResponse(Call<List<ChatMessage>> call,
                                           Response<List<ChatMessage>> res) {

                        rv.setAdapter(new ChatListAdapter(res.body(), myId));
                    }

                    @Override
                    public void onFailure(Call<List<ChatMessage>> call, Throwable t) {
                        Toast.makeText(ChatListActivity.this,
                                t.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
    }
}