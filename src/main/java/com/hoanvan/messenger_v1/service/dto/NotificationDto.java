package com.hoanvan.messenger_v1.service.dto;

import com.hoanvan.messenger_v1.entity.enums.NotificationType;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.UuidGenerator;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class NotificationDto {
    String id;
    String chatRoomId;
    String messageId;
    String createdBy;
    String content;
    NotificationType notificationType;
}
