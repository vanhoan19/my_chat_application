package com.hoanvan.messenger_v1.entity;

import com.hoanvan.messenger_v1.entity.enums.Status;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.UuidGenerator;

import java.time.LocalDate;

@Entity
@Table(name = "account")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AccountEntity extends AbstractAuditingEntity<String> {
    @Id
    @UuidGenerator
    String id;

    @Column(name = "username", unique = true, updatable = false)
    String username;

    @Column(name = "password")
    String password;

    @Column(name = "nickname", updatable = true)
    String nickname;

    @Column(name = "dob")
    LocalDate dob;

    @Column(name = "avatar", columnDefinition = "text")
    String avatar;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    Status status = Status.OFFLINE;
}
