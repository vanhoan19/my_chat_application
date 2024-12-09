package com.hoanvan.messenger_v1.service.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.hoanvan.messenger_v1.entity.enums.Status;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.UuidGenerator;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AccountDto {
    String id;
    String username;
    String nickname;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    LocalDate dob;
    String avatar;
    Status status;
}
