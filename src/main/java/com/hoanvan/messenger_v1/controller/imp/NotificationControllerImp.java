package com.hoanvan.messenger_v1.controller.imp;

import com.hoanvan.messenger_v1.controller.NotificationController;
import com.hoanvan.messenger_v1.service.dto.request.ReadNotificationRequest;
import com.hoanvan.messenger_v1.service.imp.NotificationServiceImp;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
public class NotificationControllerImp implements NotificationController {
    private final NotificationServiceImp notificationServiceImp;
    @Override
    public void markReadNotification(ReadNotificationRequest request) {
        notificationServiceImp.markReadNotification(request);
    }
}
