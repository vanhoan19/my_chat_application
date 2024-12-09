package com.hoanvan.messenger_v1.repository;

import com.hoanvan.messenger_v1.entity.FriendEntity;
import com.hoanvan.messenger_v1.entity.MessageEmojiEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MessageEmojiRepository extends JpaRepository<MessageEmojiEntity, String> {
    
}
