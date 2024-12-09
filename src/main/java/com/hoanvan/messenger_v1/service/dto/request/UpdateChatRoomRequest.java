package com.hoanvan.messenger_v1.service.dto.request;

import com.hoanvan.messenger_v1.entity.enums.ChatRoomType;
import com.hoanvan.messenger_v1.entity.enums.Status;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class UpdateChatRoomRequest {
    String roomName;
    MultipartFile roomAvatar;
    String createdBy; // được tạo bởi ai
    int numberOfMembers;
    ChatRoomType chatRoomType;
    Status status; // trang thái hoạt động của phòng chat,
}
