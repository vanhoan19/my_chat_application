package com.hoanvan.messenger_v1.service.dto;


import com.hoanvan.messenger_v1.entity.enums.ChatRoomRole;
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
public class ChatRoomMemberDto{
    String id;
    String chatRoomId;
    String accountId;
    ChatRoomRole role;
    String nickname;
    Boolean isActive; // xem người dùng còn trong nhóm chat hay đã rời khởi nhóm rồi
}
