package com.hoanvan.messenger_v1.repository;

import com.hoanvan.messenger_v1.entity.FriendEntity;
import com.hoanvan.messenger_v1.entity.MessageEntity;
import com.hoanvan.messenger_v1.service.dto.MessageDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MessageRepository extends JpaRepository<MessageEntity, String> {
    List<MessageEntity> findAllByChatRoomId(String chatRoomId);

    @Query(value = """
            SELECT
                m.id AS messageId,
                m.chat_room_id AS chatRoomId,
                m.sender_id AS senderId,
                a.avatar AS avatar,
                a.username AS username,
                crm.nickname AS nickname,
                m.content AS content,
                m.last_modified_date AS lastModifiedDate,
                GROUP_CONCAT(att.file_path) AS fileAttachments
            FROM message m
            JOIN account a ON m.sender_id = a.id
            JOIN chat_room_member crm ON m.chat_room_id = crm.chat_room_id AND crm.account_id = m.sender_id
            LEFT JOIN attachment att ON m.id = att.message_id
            WHERE m.chat_room_id = :chatRoomId
            GROUP BY m.id, m.chat_room_id, m.sender_id, a.avatar, a.username, crm.nickname, m.content, m.last_modified_date
            ORDER BY m.last_modified_date DESC
            """, nativeQuery = true)
    List<Object[]> findMessagesByChatRoomId(@Param("chatRoomId") String chatRoomId);
}

