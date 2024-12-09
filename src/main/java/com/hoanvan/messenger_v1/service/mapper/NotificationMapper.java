package com.hoanvan.messenger_v1.service.mapper;

import com.hoanvan.messenger_v1.entity.NotificationAccountEntity;
import com.hoanvan.messenger_v1.entity.NotificationEntity;
import com.hoanvan.messenger_v1.service.dto.NotificationAccountDto;
import com.hoanvan.messenger_v1.service.dto.NotificationDto;
import org.mapstruct.Mapper;

@Mapper(config = DefaultConfigMapper.class)
public interface NotificationMapper extends EntityMapper<NotificationDto, NotificationEntity>{
}
