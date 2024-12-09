package com.hoanvan.messenger_v1.service.dto.response;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class ChatRoomMessagesResponse {
    String messageId;
    String chatRoomId;
    String senderId;
    String avatar;
    String username;
    String nickname; // trong bảng chat_room_member
    String notificationId;
    String contentNotification;
    String contentMessage;
    List<String> fileAttachments;
    LocalDateTime lastModifiedDate;
}
