package com.hoanvan.messenger_v1.service.imp;

import com.hoanvan.messenger_v1.entity.AccountEntity;
import com.hoanvan.messenger_v1.entity.enums.Status;
import com.hoanvan.messenger_v1.exception.AppException;
import com.hoanvan.messenger_v1.exception.ErrorCode;
import com.hoanvan.messenger_v1.integration.minio.MinioChannel;
import com.hoanvan.messenger_v1.repository.AccountRepository;
import com.hoanvan.messenger_v1.service.AccountService;
import com.hoanvan.messenger_v1.service.dto.AccountDto;
import com.hoanvan.messenger_v1.service.dto.request.LoginRequest;
import com.hoanvan.messenger_v1.service.dto.request.RegisterAccountRequest;
import com.hoanvan.messenger_v1.service.dto.request.UpdateAccountRequest;
import com.hoanvan.messenger_v1.service.mapper.AccountMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class AccountServiceImp implements AccountService {
    private final AccountRepository accountRepository;
    private final AccountMapper accountMapper;
    private final PasswordEncoder passwordEncoder;
    private final MinioChannel minioChannel;

    @Override
    public AccountDto register(RegisterAccountRequest request) {
        if (accountRepository.existsByUsername(request.getUsername())) throw new AppException(ErrorCode.EXISTED_USER);
        String avatarUrl = (request.getAvatar() != null && request.getAvatar().getSize() > 0) ? minioChannel.upload(request.getAvatar()) : "http://localhost:9000/resources/default-avatar.jpg";
        AccountEntity account = AccountEntity.builder()
                .username(request.getUsername())
                .nickname(request.getNickname())
//                .password(passwordEncoder.encode(request.getPassword()))
                .password(request.getPassword())
                .avatar(avatarUrl)
                .dob(request.getDob())
                .status(Status.OFFLINE)
                .build();
        return accountMapper.toDto(accountRepository.save(account));
    }

    @Override
    public AccountDto login(LoginRequest request) {
//        String passwordHash = passwordEncoder.encode(request.getPassword());
//        log.info("username: {}", request.getUsername());
//        log.info("password: {}", request.getPassword());
//        log.info("passwordHash: {}", passwordHash);
        var account = accountRepository.findByUsernameAndPassword(
                request.getUsername(), request.getPassword()).orElseThrow(() ->  new AppException(ErrorCode.INVALID_USERNAME_PASSWORD));
        account.setStatus(Status.ONLINE);
        accountRepository.save(account);
        return accountMapper.toDto(account);
    }

    @Override
    public AccountDto update(String id, UpdateAccountRequest updatedAccount) {
        return accountMapper.toDto(accountRepository.findById(id).map(account -> {
            account.setPassword(updatedAccount.getPassword());
            account.setNickname(updatedAccount.getNickname());
            account.setDob(updatedAccount.getDob());
            account.setAvatar(minioChannel.upload(updatedAccount.getAvatar()));
            account.setStatus(Status.OFFLINE);
            return accountRepository.save(account); })
                .orElseThrow(() -> new AppException(ErrorCode.EXISTED_USER)));
    }

    @Override
    public AccountDto logout(String userId) {
        var account = accountRepository.findById(userId).orElseThrow(() ->  new AppException(ErrorCode.NOT_EXISTED_USER));
        account.setStatus(Status.OFFLINE);
        accountRepository.save(account);
        return accountMapper.toDto(account);
    }

    @Override
    public List<AccountDto> findAccountsByNickname(String nickname) {
        return accountRepository.findAccountsByNickname(nickname).stream().map(accountMapper::toDto).toList();
    }

    @Override
    public List<AccountDto> findAllAccounts() {
        return accountRepository.findAll().stream().map(accountMapper::toDto).toList();
    }
}
