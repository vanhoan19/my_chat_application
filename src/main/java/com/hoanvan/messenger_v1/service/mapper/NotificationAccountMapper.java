package com.hoanvan.messenger_v1.service.mapper;

import com.hoanvan.messenger_v1.entity.MessageEntity;
import com.hoanvan.messenger_v1.entity.NotificationAccountEntity;
import com.hoanvan.messenger_v1.service.dto.MessageDto;
import com.hoanvan.messenger_v1.service.dto.NotificationAccountDto;
import org.mapstruct.Mapper;

@Mapper(config = DefaultConfigMapper.class)
public interface NotificationAccountMapper extends EntityMapper<NotificationAccountDto, NotificationAccountEntity>{
}
