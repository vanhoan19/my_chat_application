package com.hoanvan.messenger_v1.service.dto.request;

import com.hoanvan.messenger_v1.entity.enums.Status;
import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.validator.constraints.Length;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Nullable;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RegisterAccountRequest {
    @NotBlank(message = "NOT_BLANK_USERNAME")
    String username;
    @NotBlank(message = "NOT_BLANK_PASSWORD")
    @Length(min = 6, message = "INVALID_LENGTH_PASSWORD")
//    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d).+$", message = "INVALID_PASSWORD")
    String password;
    @NotBlank(message = "NOT_BLANK_REPASSWORD")
    String rePassword;
    @NotBlank(message = "NOT_BLANK_NICKNAME")
    String nickname;
    @NotNull(message = "NOT_NULL_DOB")
    LocalDate dob;
    MultipartFile avatar;

    @AssertTrue(message = "WRONG_REPASSWORD")
    private boolean isPasswordsMatching() { return password != null && password.equals(rePassword); }
}
