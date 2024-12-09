package com.hoanvan.messenger_v1.service.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CreateIndividualChatRoomRequest {
    @NotBlank(message = "")
    String createdBy; // được tạo bởi ai
    @NotBlank(message = "")
    String otherId; // người còn lại
}
