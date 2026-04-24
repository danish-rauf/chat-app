package com.chatapp.controller;

import com.chatapp.entity.Message;
import com.chatapp.repository.MessageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/messages")
@RequiredArgsConstructor
public class MessageController {

    private final MessageRepository repo;

    // 🔥 REAL CHAT LIST (LATEST MESSAGE PER USER)
    @GetMapping("/chats/{userId}")
    public List<Message> getChats(@PathVariable Long userId) {

        List<Message> all = repo.findChats(userId);

        Map<Long, Message> latestMap = new HashMap<>();

        for (Message m : all) {

            Long otherId = m.getSenderId().equals(userId)
                    ? m.getReceiverId()
                    : m.getSenderId();

            if (!latestMap.containsKey(otherId)) {
                latestMap.put(otherId, m);
            } else {
                if (m.getTimestamp().isAfter(latestMap.get(otherId).getTimestamp())) {
                    latestMap.put(otherId, m);
                }
            }
        }

        return new ArrayList<>(latestMap.values());
    }

    // ================= SEEN MESSAGE =================
    @PutMapping("/seen/{id}")
    public void markSeen(@PathVariable String id) {

        Message msg = repo.findById(id).orElseThrow();
        msg.setSeen(true);
        repo.save(msg);
    }
}