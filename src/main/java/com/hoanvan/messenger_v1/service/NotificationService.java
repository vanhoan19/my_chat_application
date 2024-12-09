package com.hoanvan.messenger_v1.service;

import com.hoanvan.messenger_v1.service.dto.request.ReadNotificationRequest;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/notification")
public interface NotificationService {
    void markReadNotification(ReadNotificationRequest request);
}
