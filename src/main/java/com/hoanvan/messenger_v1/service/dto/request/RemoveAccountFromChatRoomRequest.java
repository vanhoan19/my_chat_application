package com.hoanvan.messenger_v1.service.dto.request;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RemoveAccountFromChatRoomRequest {
    String accountId;
}
