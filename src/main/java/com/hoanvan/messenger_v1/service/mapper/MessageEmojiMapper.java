package com.hoanvan.messenger_v1.service.mapper;

import com.hoanvan.messenger_v1.entity.MessageEmojiEntity;
import com.hoanvan.messenger_v1.entity.MessageEntity;
import com.hoanvan.messenger_v1.service.dto.MessageDto;
import com.hoanvan.messenger_v1.service.dto.MessageEmojiDto;
import org.mapstruct.Mapper;

@Mapper(config = DefaultConfigMapper.class)
public interface MessageEmojiMapper extends EntityMapper<MessageEmojiDto, MessageEmojiEntity>{
}
