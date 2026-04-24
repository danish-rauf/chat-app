package com.chatapp.dto;

import lombok.Data;

@Data
public class ChatMessage {

    private Long senderId;
    private Long receiverId;
    private String content;
    private String imageUrl;
    private String type; // MESSAGE, IMAGE, TYPING
}