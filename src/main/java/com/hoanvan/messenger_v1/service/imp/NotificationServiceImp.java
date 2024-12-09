package com.hoanvan.messenger_v1.service.imp;

import com.hoanvan.messenger_v1.entity.NotificationAccountEntity;
import com.hoanvan.messenger_v1.repository.NotificationAccountRepository;
import com.hoanvan.messenger_v1.service.NotificationService;
import com.hoanvan.messenger_v1.service.dto.request.ReadNotificationRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class NotificationServiceImp implements NotificationService {
    private final NotificationAccountRepository notificationAccountRepository;
    @Override
    public void markReadNotification(ReadNotificationRequest request) {
        NotificationAccountEntity entity = NotificationAccountEntity.builder()
                .notificationId(request.getNotificationId())
                .accountId(request.getAccountId())
                .build();
        notificationAccountRepository.save(entity);
    }
}
