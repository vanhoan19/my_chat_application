package com.hoanvan.messenger_v1.controller.imp;

import com.hoanvan.messenger_v1.controller.ChatRoomController;
import com.hoanvan.messenger_v1.repository.ChatRoomMemberRepository;
import com.hoanvan.messenger_v1.service.dto.ChatRoomDto;
import com.hoanvan.messenger_v1.service.dto.request.CreateGroupChatRoomRequest;
import com.hoanvan.messenger_v1.service.dto.request.CreateIndividualChatRoomRequest;
import com.hoanvan.messenger_v1.service.dto.request.UpdateChatRoomRequest;
import com.hoanvan.messenger_v1.service.dto.response.ApiResponse;
import com.hoanvan.messenger_v1.service.dto.response.ChatRoomNotificationResponse;
import com.hoanvan.messenger_v1.service.imp.ChatRoomServiceImp;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
//@MessageMapping("chatroom")
public class ChatRoomControllerImp implements ChatRoomController {
    private final SimpMessagingTemplate simpMessagingTemplate;
    private final ChatRoomMemberRepository chatRoomMemberRepository;
    private final ChatRoomServiceImp chatRoomServiceImp;

    @Override
    public void createIndividualChatRoom(CreateIndividualChatRoomRequest request) {
        ApiResponse<ChatRoomNotificationResponse> response = ApiResponse.<ChatRoomNotificationResponse>builder()
                .data(chatRoomServiceImp.createIndividualChatRoom(request))
                .build();
        if (response.getCode() == 1000) {
            simpMessagingTemplate.convertAndSend("/chat/" + request.getCreatedBy() + "/queue/new-chatroom", response);
            simpMessagingTemplate.convertAndSend("/chat/" + request.getOtherId() + "/queue/new-chatroom", response);
        }
    }

    @Override
    public void createGroupChatRoom(CreateGroupChatRoomRequest request) {
        ApiResponse<ChatRoomNotificationResponse> response = ApiResponse.<ChatRoomNotificationResponse>builder()
                .data(chatRoomServiceImp.createGroupChatRoom(request))
                .build();
        if (response.getCode() == 1000) {
            simpMessagingTemplate.convertAndSend("/chat/" + request.getCreatedBy() + "/queue/new-chatroom", response);
            List<String> memberIds = List.of(request.getMemberIds().split("\\|"));
            memberIds.forEach(memberId -> {
                simpMessagingTemplate.convertAndSend("/chat/" + memberId +"/queue/new-chatroom", response);
            });
        }
    }

    @Override
    public ApiResponse<List<ChatRoomNotificationResponse>> findAllChatRoomNotificationsByUserId(String userId) {
        return ApiResponse.<List<ChatRoomNotificationResponse>>builder()
                .data(chatRoomServiceImp.findAllChatRoomNotificationsByUserId(userId))
                .build();
    }

    @Override
    public ApiResponse<List<String>> findAllChatRoomsByUserId(String userId) {
        return ApiResponse.<List<String>>builder()
                .data(chatRoomServiceImp.findAllChatRoomsByUserId(userId))
                .build();
    }

    @Override
    public ApiResponse<ChatRoomDto> updateChatRoom(String id, UpdateChatRoomRequest request) {
        return ApiResponse.<ChatRoomDto>builder()
                .data(chatRoomServiceImp.updateChatRoom(id, request))
                .build();
    }
}
