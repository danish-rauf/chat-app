package com.chatapp.mongo;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document(collection = "messages")
public class ChatMessageMongo {

    @Id
    private String id;

    @Indexed
    private Long senderId;

    @Indexed
    private Long receiverId;

    private String content;

    private boolean delivered;
    private boolean seen;

    @Indexed
    private LocalDateTime timestamp;
}