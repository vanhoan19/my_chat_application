package com.hoanvan.messenger_v1.service.dto.request;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class ReadNotificationRequest {
    String notificationId;
    String accountId;
}
