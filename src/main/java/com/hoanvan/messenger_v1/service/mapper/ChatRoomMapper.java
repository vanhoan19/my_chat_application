package com.hoanvan.messenger_v1.service.mapper;

import com.hoanvan.messenger_v1.entity.AttachmentEntity;
import com.hoanvan.messenger_v1.entity.ChatRoomEntity;
import com.hoanvan.messenger_v1.service.dto.AttachmentDto;
import com.hoanvan.messenger_v1.service.dto.ChatRoomDto;
import com.hoanvan.messenger_v1.service.dto.ChatRoomMemberDto;
import org.mapstruct.Mapper;

@Mapper(config = DefaultConfigMapper.class)
public interface ChatRoomMapper extends EntityMapper<ChatRoomDto, ChatRoomEntity>{
}
