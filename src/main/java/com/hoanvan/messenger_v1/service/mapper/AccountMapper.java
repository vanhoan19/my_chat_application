package com.hoanvan.messenger_v1.service.mapper;

import com.hoanvan.messenger_v1.entity.AccountEntity;
import com.hoanvan.messenger_v1.service.dto.AccountDto;
import org.mapstruct.Mapper;

@Mapper(config = DefaultConfigMapper.class)
public interface AccountMapper extends EntityMapper<AccountDto, AccountEntity>{
}
