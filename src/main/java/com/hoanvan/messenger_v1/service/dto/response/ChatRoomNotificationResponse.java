package com.hoanvan.messenger_v1.service.dto.response;

import com.hoanvan.messenger_v1.entity.enums.ChatRoomType;
import com.hoanvan.messenger_v1.entity.enums.NotificationType;
import com.hoanvan.messenger_v1.entity.enums.Status;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ChatRoomNotificationResponse {
    String chatRoomId;
    String roomName;
    String roomAvatar;
    ChatRoomType chatRoomType;
    Status status = Status.ONLINE;
    String notificationId;
    String content;
    NotificationType notificationType;
    Boolean isRead = false;
    LocalDateTime lastModifiedDate;
}
