package com.hoanvan.messenger_v1.service.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class LoginRequest {
    @NotBlank(message = "NOT_BLANK_USERNAME")
    String username;
    @NotBlank(message = "NOT_BLANK_PASSWORD")
    String password;
}
