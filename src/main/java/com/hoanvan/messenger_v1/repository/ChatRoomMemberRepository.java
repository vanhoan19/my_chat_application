package com.hoanvan.messenger_v1.repository;

import com.hoanvan.messenger_v1.entity.AttachmentEntity;
import com.hoanvan.messenger_v1.entity.ChatRoomMemberEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ChatRoomMemberRepository extends JpaRepository<ChatRoomMemberEntity, String> {
    Optional<ChatRoomMemberEntity> findByChatRoomIdAndAccountId(String chatRoomId, String accountId);
}
