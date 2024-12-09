package com.hoanvan.messenger_v1.repository;

import com.hoanvan.messenger_v1.entity.AccountEntity;
import com.hoanvan.messenger_v1.entity.AttachmentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AttachmentRepository extends JpaRepository<AttachmentEntity, String> {
    
}
