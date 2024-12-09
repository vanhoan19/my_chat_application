package com.hoanvan.messenger_v1.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.UuidGenerator;

@Entity
@Table(name = "friend")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class FriendEntity extends AbstractAuditingEntity<String> {
    @Id
    @UuidGenerator
    String id;

    @Column(name = "account_id")
    String accountId;

    @Column(name = "friend_id")
    String friendId;
}
