package com.hoanvan.messenger_v1.repository;

import com.hoanvan.messenger_v1.entity.MessageEntity;
import com.hoanvan.messenger_v1.entity.NotificationAccountEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NotificationAccountRepository extends JpaRepository<NotificationAccountEntity, String> {
    
}
