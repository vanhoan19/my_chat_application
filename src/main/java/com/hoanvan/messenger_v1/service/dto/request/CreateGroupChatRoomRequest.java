package com.hoanvan.messenger_v1.service.dto.request;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CreateGroupChatRoomRequest {
    String createdBy; // được tạo bởi ai
    String memberIds; // danh sách các thành viên
    String roomName;
    MultipartFile roomAvatar;
}
