package com.chatapp.websocket;

import com.chatapp.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.*;

@Component
@RequiredArgsConstructor
public class WebSocketEvents {

    private final UserRepository repo;

    @EventListener
    public void handleConnect(SessionConnectEvent event) {
        // mark online (simplified)
    }

    @EventListener
    public void handleDisconnect(SessionDisconnectEvent event) {
        // mark offline
    }
}