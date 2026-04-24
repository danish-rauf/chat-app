package com.example.chatapp.utils;

import ua.naiksoftware.stomp.Stomp;
import ua.naiksoftware.stomp.StompClient;

public class StompManager {

    private static StompClient stompClient;

    public static void connect() {
        stompClient = Stomp.over(
                Stomp.ConnectionProvider.OKHTTP,
                "ws://10.0.2.2:8080/chat/websocket"
        );

        stompClient.connect();
    }

    public static StompClient getClient() {
        return stompClient;
    }
}