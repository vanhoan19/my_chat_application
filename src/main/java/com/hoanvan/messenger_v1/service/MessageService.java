package com.hoanvan.messenger_v1.service;

import com.hoanvan.messenger_v1.service.dto.MessageDto;
import com.hoanvan.messenger_v1.service.dto.request.SendMessageRequest;
import com.hoanvan.messenger_v1.service.dto.response.ChatRoomMessagesResponse;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@RequestMapping("/message")
public interface MessageService {
    ChatRoomMessagesResponse saveMessage(SendMessageRequest request);
    List<ChatRoomMessagesResponse> findAllMessageByChatRoomId(String chatRoomId);
}
