package com.hoanvan.messenger_v1.service.mapper;

import com.hoanvan.messenger_v1.entity.AccountEntity;
import com.hoanvan.messenger_v1.entity.AttachmentEntity;
import com.hoanvan.messenger_v1.service.dto.AccountDto;
import com.hoanvan.messenger_v1.service.dto.AttachmentDto;
import org.mapstruct.Mapper;

@Mapper(config = DefaultConfigMapper.class)
public interface AttachmentMapper extends EntityMapper<AttachmentDto, AttachmentEntity>{
}
