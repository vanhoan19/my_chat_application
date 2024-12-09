package com.hoanvan.messenger_v1.controller.imp;

import com.hoanvan.messenger_v1.controller.MessageController;
import com.hoanvan.messenger_v1.service.MessageService;
import com.hoanvan.messenger_v1.service.dto.MessageDto;
import com.hoanvan.messenger_v1.service.dto.request.SendMessageRequest;
import com.hoanvan.messenger_v1.service.dto.response.ApiResponse;
import com.hoanvan.messenger_v1.service.dto.response.ChatRoomMessagesResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
public class MessageControllerImp implements MessageController {
    private final SimpMessagingTemplate messagingTemplate;
    private final MessageService messageService;

    @Override
    public void saveMessage(SendMessageRequest request) {
        ChatRoomMessagesResponse chatRoomMessagesResponse = messageService.saveMessage(request);
        messagingTemplate.convertAndSend("/chat/chat-room/" + request.getChatRoomId(), chatRoomMessagesResponse);
    }


    @Override
    public ApiResponse<List<ChatRoomMessagesResponse>> getMessagesByChatRoomId(String chatRoomId) {
        return ApiResponse.<List<ChatRoomMessagesResponse>>builder()
                .data(messageService.findAllMessageByChatRoomId(chatRoomId))
                .build();
    }
}
