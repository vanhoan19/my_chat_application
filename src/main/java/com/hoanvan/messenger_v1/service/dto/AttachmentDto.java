package com.hoanvan.messenger_v1.service.dto;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.UuidGenerator;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AttachmentDto {
    String id;
    String messageId;
    String fileName;
    String filePath;
    String fileType;
    Double fileSize; // chuyển về MB
}
