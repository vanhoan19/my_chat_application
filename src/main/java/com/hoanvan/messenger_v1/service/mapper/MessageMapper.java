package com.hoanvan.messenger_v1.service.mapper;

import com.hoanvan.messenger_v1.entity.FriendEntity;
import com.hoanvan.messenger_v1.entity.MessageEntity;
import com.hoanvan.messenger_v1.service.dto.FriendDto;
import com.hoanvan.messenger_v1.service.dto.MessageDto;
import org.mapstruct.Mapper;

@Mapper(config = DefaultConfigMapper.class)
public interface MessageMapper extends EntityMapper<MessageDto, MessageEntity>{
}
