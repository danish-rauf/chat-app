package com.example.chatapp.adapter;

import android.content.Intent;
import android.view.*;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.chatapp.R;
import com.example.chatapp.model.ChatMessage;
import com.example.chatapp.ui.ChatActivity;

import java.util.List;

public class ChatListAdapter extends RecyclerView.Adapter<ChatListAdapter.VH> {

    List<ChatMessage> list;
    Long myId;

    public ChatListAdapter(List<ChatMessage> list, Long myId) {
        this.list = list;
        this.myId = myId;
    }

    static class VH extends RecyclerView.ViewHolder {
        TextView name, lastMsg;

        VH(View v) {
            super(v);
            name = v.findViewById(R.id.name);
            lastMsg = v.findViewById(R.id.lastMsg);
        }
    }

    @NonNull
    @Override
    public VH onCreateViewHolder(@NonNull ViewGroup p, int v) {
        return new VH(LayoutInflater.from(p.getContext())
                .inflate(R.layout.item_chat, p, false));
    }

    @Override
    public void onBindViewHolder(@NonNull VH h, int i) {

        ChatMessage msg = list.get(i);

        Long other = msg.senderId.equals(myId)
                ? msg.receiverId : msg.senderId;

        h.name.setText("User " + other);
        h.lastMsg.setText(msg.content);

        h.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(v.getContext(), ChatActivity.class);
            intent.putExtra("receiverId", other);
            v.getContext().startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}