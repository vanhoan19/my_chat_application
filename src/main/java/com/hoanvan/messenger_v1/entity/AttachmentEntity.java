package com.hoanvan.messenger_v1.entity;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.UuidGenerator;

@Entity
@Table(name = "attachment")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AttachmentEntity extends AbstractAuditingEntity<String> {
    @Id
    @UuidGenerator
    String id;

    @Column(name = "message_id")
    String messageId;

    @Column(name = "file_name")
    String fileName;

    @Column(name = "file_path", columnDefinition = "text")
    String filePath;

    @Column(name = "file_type")
    String fileType;

    @Column(name = "file_size")
    Double fileSize; // chuyển về MB
}
