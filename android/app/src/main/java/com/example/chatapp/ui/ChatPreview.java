package com.example.chatapp.model;

public class ChatPreview {

    String name;
    String lastMessage;
    Long userId;

    public ChatPreview(String name, String lastMessage, Long userId) {
        this.name = name;
        this.lastMessage = lastMessage;
        this.userId = userId;
    }

    public String getName() { return name; }
    public String getLastMessage() { return lastMessage; }
    public Long getUserId() { return userId; }
}