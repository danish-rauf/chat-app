package com.chatapp.websocket;

import com.chatapp.service.UserPresenceService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.*;

@Component
@RequiredArgsConstructor
public class WebSocketEventListener {

    private final UserPresenceService presenceService;

    @EventListener
    public void handleConnect(SessionConnectEvent event) {

        StompHeaderAccessor accessor =
                StompHeaderAccessor.wrap(event.getMessage());

        String userId = accessor.getFirstNativeHeader("userId");

        if (userId != null) {
            presenceService.setOnline(Long.parseLong(userId));
        }
    }

    @EventListener
    public void handleDisconnect(SessionDisconnectEvent event) {

        StompHeaderAccessor accessor =
                StompHeaderAccessor.wrap(event.getMessage());

        String userId = accessor.getFirstNativeHeader("userId");

        if (userId != null) {
            presenceService.setOffline(Long.parseLong(userId));
        }
    }
}