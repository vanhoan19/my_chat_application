package com.hoanvan.messenger_v1.controller;

import com.hoanvan.messenger_v1.service.dto.request.ReadNotificationRequest;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/notification")
public interface NotificationController {
    @PostMapping(value = "/mark-read-notification", consumes = MediaType.APPLICATION_JSON_VALUE)
    void markReadNotification(@RequestBody ReadNotificationRequest request);
}
