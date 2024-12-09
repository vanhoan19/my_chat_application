package com.hoanvan.messenger_v1.controller;

import com.hoanvan.messenger_v1.entity.AccountEntity;
import com.hoanvan.messenger_v1.service.dto.AccountDto;
import com.hoanvan.messenger_v1.service.dto.request.LoginRequest;
import com.hoanvan.messenger_v1.service.dto.request.RegisterAccountRequest;
import com.hoanvan.messenger_v1.service.dto.request.UpdateAccountRequest;
import com.hoanvan.messenger_v1.service.dto.response.ApiResponse;
import jakarta.validation.Valid;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/account")
public interface AccountController {
    @PostMapping(value = "/register", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    ApiResponse<AccountDto> register(@Valid RegisterAccountRequest request);

    @PostMapping(value = "/login", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    ApiResponse<AccountDto> login(@Valid LoginRequest request);

    @PostMapping(value = "/update/{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    ApiResponse<AccountDto> update(@PathVariable("id") String id, @Valid UpdateAccountRequest request);

    @PostMapping(value = "/logout/{id}")
    ApiResponse<AccountDto> logout(@PathVariable("id") String id);

    @PostMapping(value = "/find-accounts-by-nickname/{nickname}")
    ApiResponse<List<AccountDto>> findAccountsByNickname(@PathVariable("nickname") String nickname);

    @PostMapping(value = "/get-all-accounts")
    ApiResponse<List<AccountDto>> findAccountsByNickname();
}
