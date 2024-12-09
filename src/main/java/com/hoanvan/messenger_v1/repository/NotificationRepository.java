package com.hoanvan.messenger_v1.repository;

import com.hoanvan.messenger_v1.entity.MessageEntity;
import com.hoanvan.messenger_v1.entity.NotificationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NotificationRepository extends JpaRepository<NotificationEntity, String> {
    
}
