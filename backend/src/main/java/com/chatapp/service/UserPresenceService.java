package com.chatapp.service;

import org.springframework.stereotype.Service;

import java.util.concurrent.ConcurrentHashMap;

@Service
public class UserPresenceService {

    private final ConcurrentHashMap<Long, Boolean> onlineUsers = new ConcurrentHashMap<>();

    public void setOnline(Long userId) {
        onlineUsers.put(userId, true);
    }

    public void setOffline(Long userId) {
        onlineUsers.put(userId, false);
    }

    public boolean isOnline(Long userId) {
        return onlineUsers.getOrDefault(userId, false);
    }
}