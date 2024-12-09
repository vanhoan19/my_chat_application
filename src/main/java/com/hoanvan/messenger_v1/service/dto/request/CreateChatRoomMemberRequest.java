package com.hoanvan.messenger_v1.service.dto.request;

import com.hoanvan.messenger_v1.entity.enums.ChatRoomRole;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CreateChatRoomMemberRequest {
    String chatRoomId;
    String accountId;
    ChatRoomRole role;
    String nickname;
}
