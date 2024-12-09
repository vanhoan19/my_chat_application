package com.hoanvan.messenger_v1.controller;

import com.hoanvan.messenger_v1.service.dto.ChatRoomDto;
import com.hoanvan.messenger_v1.service.dto.request.CreateGroupChatRoomRequest;
import com.hoanvan.messenger_v1.service.dto.request.CreateIndividualChatRoomRequest;
import com.hoanvan.messenger_v1.service.dto.request.UpdateChatRoomRequest;
import com.hoanvan.messenger_v1.service.dto.response.ApiResponse;
import com.hoanvan.messenger_v1.service.dto.response.ChatRoomNotificationResponse;
import jakarta.validation.Valid;
import org.springframework.http.MediaType;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@RequestMapping("/chatroom")
public interface ChatRoomController {
    @PostMapping("/create-individual-chatroom")
    void createIndividualChatRoom(@Valid @RequestBody CreateIndividualChatRoomRequest request);
    @PostMapping(value = "/create-group-chatroom", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    void createGroupChatRoom(@Valid CreateGroupChatRoomRequest request);
    @PostMapping(value = "/get-all-chatroom-notification/{userId}")
    ApiResponse<List<ChatRoomNotificationResponse>> findAllChatRoomNotificationsByUserId(@PathVariable("userId") String userId);
    @PostMapping(value = "/get-all-chatroom/{userId}")
    ApiResponse<List<String>> findAllChatRoomsByUserId(@PathVariable("userId") String userId);
    @PostMapping(value = "/update/{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    ApiResponse<ChatRoomDto> updateChatRoom(@PathVariable("id") String id, @Valid UpdateChatRoomRequest request);
}
