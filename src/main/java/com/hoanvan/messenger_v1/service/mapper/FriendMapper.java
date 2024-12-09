package com.hoanvan.messenger_v1.service.mapper;

import com.hoanvan.messenger_v1.entity.AccountEntity;
import com.hoanvan.messenger_v1.entity.FriendEntity;
import com.hoanvan.messenger_v1.service.dto.AccountDto;
import com.hoanvan.messenger_v1.service.dto.FriendDto;
import org.mapstruct.Mapper;

@Mapper(config = DefaultConfigMapper.class)
public interface FriendMapper extends EntityMapper<FriendDto, FriendEntity>{
}
