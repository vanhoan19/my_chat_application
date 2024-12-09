package com.hoanvan.messenger_v1.entity;


import com.hoanvan.messenger_v1.entity.enums.ChatRoomRole;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.UuidGenerator;

@Entity
@Table(name = "chat_room_member")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ChatRoomMemberEntity extends  AbstractAuditingEntity<String> {
    @Id
    @UuidGenerator
    String id;

    @Column(name = "chat_room_id")
    String chatRoomId;

    @Column(name = "account_id")
    String accountId;

    @Column(name = "role")
    @Enumerated(EnumType.STRING)
    ChatRoomRole role = ChatRoomRole.MEMBER;

    @Column(name = "nickname")
    String nickname;

    @Column(name = "is_active")
    Boolean isActive = true; // xem người dùng còn trong nhóm chat hay đã rời khởi nhóm rồi
}
