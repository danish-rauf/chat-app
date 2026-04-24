package com.chatapp.websocket;

import com.chatapp.dto.ChatMessage;
import com.chatapp.entity.Message;
import com.chatapp.repository.MessageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.*;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import java.time.LocalDateTime;

@Controller
@RequiredArgsConstructor
public class ChatController {

    private final SimpMessagingTemplate messagingTemplate;
    private final MessageRepository repo;

    // ================= MESSAGE =================
    @MessageMapping("/chat.send")
    public void sendMessage(@Payload ChatMessage msg) {

        Message m = new Message();

        m.setSenderId(msg.getSenderId());
        m.setReceiverId(msg.getReceiverId());
        m.setContent(msg.getContent());
        m.setImageUrl(msg.getImageUrl());

        m.setDelivered(true);
        m.setSeen(false);
        m.setTimestamp(LocalDateTime.now());

        // 🔥 SAVE IN MONGODB
        Message saved = repo.save(m);

        // 🔥 SEND TO RECEIVER
        messagingTemplate.convertAndSendToUser(
                msg.getReceiverId().toString(),
                "/queue/messages",
                saved
        );
    }

    // ================= TYPING =================
    @MessageMapping("/chat.typing")
    public void typing(@Payload ChatMessage msg) {

        messagingTemplate.convertAndSendToUser(
                msg.getReceiverId().toString(),
                "/queue/typing",
                msg
        );
    }
}