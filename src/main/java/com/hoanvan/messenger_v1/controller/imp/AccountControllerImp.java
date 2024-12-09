package com.hoanvan.messenger_v1.controller.imp;

import com.hoanvan.messenger_v1.controller.AccountController;
import com.hoanvan.messenger_v1.entity.AccountEntity;
import com.hoanvan.messenger_v1.service.AccountService;
import com.hoanvan.messenger_v1.service.dto.AccountDto;
import com.hoanvan.messenger_v1.service.dto.request.LoginRequest;
import com.hoanvan.messenger_v1.service.dto.request.RegisterAccountRequest;
import com.hoanvan.messenger_v1.service.dto.request.UpdateAccountRequest;
import com.hoanvan.messenger_v1.service.dto.response.ApiResponse;
import com.hoanvan.messenger_v1.service.imp.AccountServiceImp;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
public class AccountControllerImp implements AccountController {
    private final AccountServiceImp accountService;

    @Override
    public ApiResponse<AccountDto> register(RegisterAccountRequest request) {
        return ApiResponse.<AccountDto>builder()
                .data(accountService.register(request))
                .build();
    }

    @Override
    public ApiResponse<AccountDto> login(LoginRequest request) {
        return ApiResponse.<AccountDto>builder()
                .data(accountService.login(request))
                .build();
    }

    @Override
    public ApiResponse<AccountDto> update(String id, UpdateAccountRequest request) {
        return ApiResponse.<AccountDto>builder()
                .data(accountService.update(id, request))
                .build();
    }

    @Override
    public ApiResponse<AccountDto> logout(String id) {
        return ApiResponse.<AccountDto>builder()
                .data(accountService.logout(id))
                .build();
    }

    @Override
    public ApiResponse<List<AccountDto>> findAccountsByNickname(String nickname) {
        return ApiResponse.<List<AccountDto>>builder()
                .data(accountService.findAccountsByNickname(nickname))
                .build();
    }

    @Override
    public ApiResponse<List<AccountDto>> findAccountsByNickname() {
        return ApiResponse.<List<AccountDto>>builder()
                .data(accountService.findAllAccounts())
                .build();
    }
}
