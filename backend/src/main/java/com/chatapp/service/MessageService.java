package com.chatapp.service;

import com.chatapp.entity.Message;
import com.chatapp.entity.User;
import com.chatapp.repository.MessageRepository;
import com.chatapp.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class MessageService {

    private final MessageRepository messageRepository;
    private final UserRepository userRepository;

    public Message saveMessage(Long senderId, Long receiverId, String content) {

        User sender = userRepository.findById(senderId).orElseThrow();
        User receiver = userRepository.findById(receiverId).orElseThrow();

        Message msg = new Message();
        msg.setContent(content);
        msg.setSender(sender);
        msg.setReceiver(receiver);
        msg.setTimestamp(LocalDateTime.now());
        msg.setSeen(false);

        return messageRepository.save(msg);
    }
}