package com.hoanvan.messenger_v1.entity;

import com.hoanvan.messenger_v1.entity.enums.NotificationType;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.UuidGenerator;

@Entity
@Table(name = "notification")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class NotificationEntity extends AbstractAuditingEntity<String> {
    @Id
    @UuidGenerator
    String id;

    @Column(name = "message_id")
    String messageId;

    @Column(name = "chat_room_id")
    String chatRoomId;

    @Column(name = "created_by")
    String createdBy; // người tạo thông báo

    @Column(name = "content")
    String content;

    @Column(name = "notification_type", length = 50)
    @Enumerated(EnumType.STRING)
    NotificationType notificationType = NotificationType.NEW_MESSAGE;
}
