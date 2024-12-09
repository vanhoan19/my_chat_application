package com.hoanvan.messenger_v1.service.dto;

import com.hoanvan.messenger_v1.entity.enums.ChatRoomType;
import com.hoanvan.messenger_v1.entity.enums.Status;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ChatRoomDto {
    String id;
    String roomName;
    String roomAvatar;
    String createdBy; // được tạo bởi ai
    int numberOfMembers;
    ChatRoomType chatRoomType;
    Status status; // trang thái hoạt động của phòng chat,
}
