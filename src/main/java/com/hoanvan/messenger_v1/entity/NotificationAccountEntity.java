package com.hoanvan.messenger_v1.entity;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.UuidGenerator;

@Entity
@Table(name = "notification_account")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class NotificationAccountEntity extends AbstractAuditingEntity<String> {
    // table lưu lại người dùng đã đọc thông báo
    @Id
    @UuidGenerator
    String id;

    @Column(name = "notification_id")
    String notificationId;

    @Column(name = "account_id")
    String accountId;
}
