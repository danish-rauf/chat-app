package com.chatapp.repository;

import com.chatapp.entity.Message;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface MessageRepository extends MongoRepository<Message, String> {

    // 🔥 Get chat between two users
    List<Message> findBySenderIdAndReceiverIdOrReceiverIdAndSenderId(
            Long sender1, Long receiver1,
            Long sender2, Long receiver2
    );

    // 🔥 Latest messages for chat list
    @Query("{ $or: [ { senderId: ?0 }, { receiverId: ?0 } ] }")
    List<Message> findChats(Long userId);
}