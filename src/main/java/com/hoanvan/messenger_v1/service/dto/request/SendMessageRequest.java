package com.hoanvan.messenger_v1.service.dto.request;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.jetbrains.annotations.NotNull;
import org.springframework.web.multipart.MultipartFile;

import java.util.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class SendMessageRequest {
    String chatRoomId;
    String senderId;
    String avatar;
    String username;
    String content;
    List<MultipartFile> fileAttachments = new ArrayList<>();
}
