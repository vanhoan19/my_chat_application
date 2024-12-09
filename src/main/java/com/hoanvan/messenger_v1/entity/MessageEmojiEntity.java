package com.hoanvan.messenger_v1.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.UuidGenerator;

@Entity
@Table(name = "message_emoji")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class MessageEmojiEntity extends AbstractAuditingEntity<String> {
    // table lưu lại người dùng đã đọc tin nhắn
    @Id
    @UuidGenerator
    String id;

    @Column(name = "message_id")
    String messageId;

    @Column(name = "account_id")
    String accountId;

    @Column(name = "emoji")
    String emoji;
}
