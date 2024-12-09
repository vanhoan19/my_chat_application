package com.hoanvan.messenger_v1.service;

import com.hoanvan.messenger_v1.service.dto.ChatRoomMemberDto;
import com.hoanvan.messenger_v1.service.dto.request.CreateChatRoomMemberRequest;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping
public interface ChatRoomMemberService {
    ChatRoomMemberDto createChatRoomMember(CreateChatRoomMemberRequest request);
}
