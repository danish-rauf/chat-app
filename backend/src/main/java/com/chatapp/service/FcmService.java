package com.chatapp.service;

import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.Message;
import org.springframework.stereotype.Service;

@Service
public class FcmService {

    public void sendNotification(String token, String messageText) {
        try {

            Message msg = Message.builder()
                    .putData("message", messageText)
                    .setToken(token)
                    .build();

            FirebaseMessaging.getInstance().sendAsync(msg);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}