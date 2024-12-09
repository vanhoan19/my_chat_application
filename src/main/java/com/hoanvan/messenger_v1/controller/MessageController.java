package com.hoanvan.messenger_v1.controller;

import com.hoanvan.messenger_v1.service.dto.ChatRoomDto;
import com.hoanvan.messenger_v1.service.dto.MessageDto;
import com.hoanvan.messenger_v1.service.dto.request.CreateGroupChatRoomRequest;
import com.hoanvan.messenger_v1.service.dto.request.CreateIndividualChatRoomRequest;
import com.hoanvan.messenger_v1.service.dto.request.SendMessageRequest;
import com.hoanvan.messenger_v1.service.dto.response.ApiResponse;
import com.hoanvan.messenger_v1.service.dto.response.ChatRoomMessagesResponse;
import jakarta.validation.Valid;
import org.springframework.http.MediaType;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@RequestMapping("/message")
public interface MessageController {
    @PostMapping(value = "/send-message", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    void saveMessage(@Valid SendMessageRequest request);

    @PostMapping("/get-messages/{chatRoomId}")
    ApiResponse<List<ChatRoomMessagesResponse>> getMessagesByChatRoomId(@PathVariable("chatRoomId") String chatRoomId);
}
