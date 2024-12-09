package com.hoanvan.messenger_v1.repository;

import com.hoanvan.messenger_v1.entity.ChatRoomEntity;
import com.hoanvan.messenger_v1.service.dto.response.ChatRoomNotificationResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChatRoomRepository extends JpaRepository<ChatRoomEntity, String> {
    @Query(value = """
            WITH LatestNotification AS (
                SELECT
                    n.chat_room_id,
                    n.id AS notification_id,
                    n.content,
                    n.notification_type,
                    n.last_modified_date,
                    n.created_by,
                    ROW_NUMBER() OVER (PARTITION BY n.chat_room_id ORDER BY n.last_modified_date DESC) AS rn
                FROM notification n
            ),
            ChatRoomStatus AS (
                SELECT
                    crm.chat_room_id,
                    CASE
                        WHEN COUNT(a.id) > 1 THEN 'ONLINE'
                        ELSE 'OFFLINE'
                    END AS status
                FROM chat_room_member crm
                JOIN account a ON crm.account_id = a.id
                WHERE crm.is_active = TRUE
                GROUP BY crm.chat_room_id
            )
            SELECT DISTINCT
                cr.id AS chatRoomId,
                CASE
                    WHEN cr.chat_room_type = 'INDIVIDUAL' THEN (
                        SELECT crm.nickname
                        FROM chat_room_member crm
                        WHERE crm.chat_room_id = cr.id AND crm.account_id <> :userId
                    )
                    ELSE cr.room_name
                END AS roomName,
                CASE
                    WHEN cr.chat_room_type = 'INDIVIDUAL' THEN (
                        SELECT a.avatar
                        FROM chat_room_member crm
                        JOIN account a ON crm.account_id = a.id
                        WHERE crm.chat_room_id = cr.id AND crm.account_id <> :userId
                    )
                    ELSE cr.room_avatar
                END AS roomAvatar,
                cr.chat_room_type AS chatRoomType,
                crs.status,
                ln.notification_id AS notificationId,
                CASE
                    WHEN ln.created_by = :userId THEN CONCAT('Bạn: ', ln.content)
                    ELSE CONCAT(
                        (SELECT crm.nickname
                        FROM chat_room_member crm
                        WHERE crm.account_id = ln.created_by AND crm.chat_room_id = ln.chat_room_id),
                        ': ', ln.content)
                END AS content,
                ln.notification_type AS notificationType,
                CASE
                    WHEN na.id IS NULL THEN FALSE
                    ELSE TRUE
                END AS isRead,
                ln.last_modified_date as lastModifiedDate
            FROM chat_room cr
            JOIN LatestNotification ln ON cr.id = ln.chat_room_id AND ln.rn = 1
            JOIN ChatRoomStatus crs ON cr.id = crs.chat_room_id
            LEFT JOIN notification_account na ON ln.notification_id = na.notification_id AND na.account_id = :userId
            WHERE EXISTS (
                SELECT 1
                FROM chat_room_member crm
                WHERE crm.chat_room_id = cr.id AND crm.account_id = :userId AND crm.is_active = TRUE
            )
            ORDER BY ln.last_modified_date DESC
            """, nativeQuery = true)
    List<Object[]> findAllChatRoomsNotificationByUserId(String userId);

    @Query(value = "select distinct chat_room_id " +
            "from chat_room_member " +
            "where account_id = :userId", nativeQuery = true)
    List<Object[]> findAllChatRoomsByUserId(String userId);

    @Query(value = "SELECT c.* FROM chat_room c " +
            "WHERE c.id IN (" +
            "   SELECT m.chat_room_id FROM chat_room_member m " +
            "   WHERE m.account_id IN :memberIds " +
            "   GROUP BY m.chat_room_id " +
            "   HAVING COUNT(m.chat_room_id) = :size" +
            ") AND (" +
            "   SELECT COUNT(*) FROM chat_room_member m2 " +
            "   WHERE m2.chat_room_id = c.id" +
            ") = :size", nativeQuery = true)
    List<ChatRoomEntity> findChatRoomByMemberIds(@Param("memberIds") List<String> memberIds, @Param("size") long size);
}