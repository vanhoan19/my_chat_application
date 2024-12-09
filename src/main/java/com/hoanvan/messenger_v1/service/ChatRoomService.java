package com.hoanvan.messenger_v1.service;

import com.hoanvan.messenger_v1.service.dto.ChatRoomDto;
import com.hoanvan.messenger_v1.service.dto.request.CreateGroupChatRoomRequest;
import com.hoanvan.messenger_v1.service.dto.request.CreateIndividualChatRoomRequest;
import com.hoanvan.messenger_v1.service.dto.request.RemoveAccountFromChatRoomRequest;
import com.hoanvan.messenger_v1.service.dto.request.UpdateChatRoomRequest;
import com.hoanvan.messenger_v1.service.dto.response.ChatRoomNotificationResponse;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@RequestMapping("/chatroom")
public interface ChatRoomService {
    ChatRoomNotificationResponse createIndividualChatRoom(CreateIndividualChatRoomRequest request);
    ChatRoomNotificationResponse createGroupChatRoom(CreateGroupChatRoomRequest request);
    ChatRoomDto removeAccountFromChatRoom(RemoveAccountFromChatRoomRequest request);
    List<ChatRoomNotificationResponse> findAllChatRoomNotificationsByUserId(String userId);
    List<String> findAllChatRoomsByUserId(String userId);
    ChatRoomDto updateChatRoom(String id, UpdateChatRoomRequest request);
}
