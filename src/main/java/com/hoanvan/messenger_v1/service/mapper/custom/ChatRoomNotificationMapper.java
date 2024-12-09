package com.hoanvan.messenger_v1.service.mapper.custom;

import com.hoanvan.messenger_v1.common.utils.ConverterUtils;
import com.hoanvan.messenger_v1.entity.enums.ChatRoomType;
import com.hoanvan.messenger_v1.entity.enums.NotificationType;
import com.hoanvan.messenger_v1.entity.enums.Status;
import com.hoanvan.messenger_v1.service.dto.response.ChatRoomNotificationResponse;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

public class ChatRoomNotificationMapper {
    public static ChatRoomNotificationResponse mapToChatRoomNotificationResponse(Object[] result) {
        Boolean isRead = (Integer) result[8] != 0;
        LocalDateTime lastModifiedDate = ConverterUtils.convertToLocalDateTimeFromObject(result[9]);
        ChatRoomNotificationResponse response = new ChatRoomNotificationResponse(
                (String) result[0], // chatRoomId
                (String) result[1], // roomName
                (String) result[2], // roomAvatar
                (ChatRoomType) Enum.valueOf(ChatRoomType.class, (String) result[3]), // chatRoomType
                (Status) Enum.valueOf(Status.class, (String) result[4]), // status
                (String) result[5], // notificationId
                (String) result[6], // content
                (NotificationType) Enum.valueOf(NotificationType.class, (String) result[7]), // notificationType
                isRead, // isRead,
                lastModifiedDate
        );
        return response;
    }
}
