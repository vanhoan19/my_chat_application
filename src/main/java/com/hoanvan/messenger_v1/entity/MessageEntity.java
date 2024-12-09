package com.hoanvan.messenger_v1.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.UuidGenerator;

@Entity
@Table(name = "message")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class MessageEntity extends AbstractAuditingEntity<String> {
    @Id
    @UuidGenerator
    String id;

    @Column(name = "chat_room_id")
    String chatRoomId;

    @Column(name = "sender_id")
    String senderId;

    @Column(name = "content")
    String content;
}
