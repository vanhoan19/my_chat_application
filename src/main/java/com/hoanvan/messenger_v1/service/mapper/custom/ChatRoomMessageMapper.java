package com.hoanvan.messenger_v1.service.mapper.custom;

import com.hoanvan.messenger_v1.common.utils.ConverterUtils;
import com.hoanvan.messenger_v1.service.dto.response.ChatRoomMessagesResponse;
import org.springframework.format.datetime.DateFormatter;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ChatRoomMessageMapper {
    public static ChatRoomMessagesResponse mapToChatRoomMessagesResponse(Object[] result) {
        List<String> fileAttachments = new ArrayList<>();
        if (result[8] != null) {
            fileAttachments = List.of(((String) result[8]).split(","));
        }

        LocalDateTime lastModifiedDate = ConverterUtils.convertToLocalDateTimeFromObject(result[7]);

        return ChatRoomMessagesResponse.builder()
                .messageId((String) result[0])
                .chatRoomId((String) result[1])
                .senderId((String) result[2])
                .avatar((String) result[3])
                .username((String) result[4])
                .nickname((String) result[5])
                .contentMessage((String) result[6])
                .lastModifiedDate(lastModifiedDate)
                .fileAttachments(fileAttachments)
                .build();
    }
}
