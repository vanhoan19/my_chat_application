package com.hoanvan.messenger_v1.repository;

import com.hoanvan.messenger_v1.entity.AccountEntity;
import com.hoanvan.messenger_v1.entity.FriendEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FriendRepository extends JpaRepository<FriendEntity, String> {
    
}
