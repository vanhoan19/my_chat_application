package com.hoanvan.messenger_v1.entity;

import com.hoanvan.messenger_v1.entity.enums.ChatRoomType;
import com.hoanvan.messenger_v1.entity.enums.Status;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.UuidGenerator;

@Entity
@Table(name = "chat_room")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ChatRoomEntity extends AbstractAuditingEntity<String> {
    @Id
    @UuidGenerator
    String id;

    @Column(name = "room_name")
    String roomName;

    @Column(name = "room_avatar", columnDefinition = "text")
    String roomAvatar;

    @Column(name = "created_by")
    String createdBy; // được tạo bởi ai

    @Column(name = "number_of_members")
    int numberOfMembers = 2;

    @Column(name = "chat_room_type", updatable = false)
    @Enumerated(EnumType.STRING)
    ChatRoomType chatRoomType = ChatRoomType.INDIVIDUAL;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    Status status = Status.OFFLINE; // trang thái hoạt động của phòng chat,
    // nếu > 1 người trong phòng online -> phòng đang hoạt dộng, ngược lại phòng không hoạt động
}
