package com.chatapp.mongo;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface ChatMessageMongoRepository
        extends MongoRepository<ChatMessageMongo, String> {

    List<ChatMessageMongo> findBySenderIdAndReceiverIdOrderByTimestampDesc(
            Long senderId,
            Long receiverId
    );

    List<ChatMessageMongo> findByReceiverIdAndSenderIdOrderByTimestampDesc(
            Long receiverId,
            Long senderId
    );

    List<ChatMessageMongo> findTop1BySenderIdAndReceiverIdOrderByTimestampDesc(
            Long senderId,
            Long receiverId
    );

    List<ChatMessageMongo> findTop1ByReceiverIdAndSenderIdOrderByTimestampDesc(
            Long receiverId,
            Long senderId
    );
}