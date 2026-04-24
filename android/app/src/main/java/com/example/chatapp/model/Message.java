package com.example.chatapp.model;

public class Message {

    private Long senderId;
    private Long receiverId;
    private String content;
    private String imageUrl;
    private boolean delivered;
    private boolean seen;

    public Message(Long senderId, Long receiverId, String content) {
        this.senderId = senderId;
        this.receiverId = receiverId;
        this.content = content;
    }

    public Long getSenderId() { return senderId; }
    public Long getReceiverId() { return receiverId; }
    public String getContent() { return content; }

    public String getImageUrl() { return imageUrl; }
    public void setImageUrl(String imageUrl) { this.imageUrl = imageUrl; }
}