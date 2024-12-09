package com.hoanvan.messenger_v1.service;

import com.hoanvan.messenger_v1.entity.AccountEntity;
import com.hoanvan.messenger_v1.service.dto.AccountDto;
import com.hoanvan.messenger_v1.service.dto.request.LoginRequest;
import com.hoanvan.messenger_v1.service.dto.request.RegisterAccountRequest;
import com.hoanvan.messenger_v1.service.dto.request.UpdateAccountRequest;

import java.util.List;

public interface AccountService {
    AccountDto register(RegisterAccountRequest request);
    AccountDto login(LoginRequest request);
    AccountDto update(String id, UpdateAccountRequest request);
    AccountDto logout(String userId);
    List<AccountDto> findAccountsByNickname(String nickname);
    List<AccountDto> findAllAccounts();
}
