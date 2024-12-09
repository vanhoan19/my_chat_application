package com.hoanvan.messenger_v1.service.imp;

import com.hoanvan.messenger_v1.entity.*;
import com.hoanvan.messenger_v1.entity.enums.ChatRoomRole;
import com.hoanvan.messenger_v1.entity.enums.ChatRoomType;
import com.hoanvan.messenger_v1.entity.enums.NotificationType;
import com.hoanvan.messenger_v1.entity.enums.Status;
import com.hoanvan.messenger_v1.exception.AppException;
import com.hoanvan.messenger_v1.exception.ErrorCode;
import com.hoanvan.messenger_v1.integration.minio.MinioChannel;
import com.hoanvan.messenger_v1.repository.*;
import com.hoanvan.messenger_v1.service.ChatRoomService;
import com.hoanvan.messenger_v1.service.dto.ChatRoomDto;
import com.hoanvan.messenger_v1.service.dto.request.CreateGroupChatRoomRequest;
import com.hoanvan.messenger_v1.service.dto.request.CreateIndividualChatRoomRequest;
import com.hoanvan.messenger_v1.service.dto.request.RemoveAccountFromChatRoomRequest;
import com.hoanvan.messenger_v1.service.dto.request.UpdateChatRoomRequest;
import com.hoanvan.messenger_v1.service.dto.response.ChatRoomNotificationResponse;
import com.hoanvan.messenger_v1.service.mapper.ChatRoomMapper;
import com.hoanvan.messenger_v1.service.mapper.custom.ChatRoomNotificationMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class ChatRoomServiceImp implements ChatRoomService {
    private final NotificationAccountRepository notificationAccountRepository;
    private final NotificationRepository notificationRepository;
    private final ChatRoomRepository chatRoomRepository;
    private final ChatRoomMemberRepository chatRoomMemberRepository;
    private final AccountRepository accountRepository;
    private final ChatRoomMapper chatRoomMapper;
    private final MinioChannel minioChannel;

    @Override
    public ChatRoomNotificationResponse createIndividualChatRoom(CreateIndividualChatRoomRequest request) {
        // Kiểm tra xem phòng chat đã tồn tại chưa
        List<String> memberIds = Arrays.asList(request.getCreatedBy(), request.getOtherId());
        if (chatRoomExists(memberIds)) throw new AppException(ErrorCode.EXISTED_CHATROOM);

        // Thêm phòng mới
        ChatRoomEntity chatRoom = chatRoomRepository.save(ChatRoomEntity.builder()
                .createdBy(request.getCreatedBy())
                .numberOfMembers(2)
                .chatRoomType(ChatRoomType.INDIVIDUAL)
                .status(Status.OFFLINE)
                .build());

        AccountEntity createdByAccount = accountRepository.findById(request.getCreatedBy())
                .orElseThrow(() -> new AppException(ErrorCode.EXISTED_USER));
        AccountEntity otherAccount = accountRepository.findById(request.getOtherId())
                .orElseThrow(() -> new AppException(ErrorCode.EXISTED_USER));

        // Thêm user vào phòng
        chatRoomMemberRepository.save(ChatRoomMemberEntity.builder()
                .chatRoomId(chatRoom.getId())
                .accountId(request.getCreatedBy())
                .nickname(createdByAccount.getNickname())
                .role(ChatRoomRole.MEMBER)
                .isActive(true)
                .build());

        // Thêm other vào phòng
        chatRoomMemberRepository.save(ChatRoomMemberEntity.builder()
                .chatRoomId(chatRoom.getId())
                .accountId(request.getOtherId())
                .nickname(otherAccount.getNickname())
                .role(ChatRoomRole.MEMBER)
                .isActive(true)
                .build());

        // Tạo thông báo thêm mới phòng chat
        NotificationEntity savedNotification = notificationRepository.save(NotificationEntity.builder()
                        .chatRoomId(chatRoom.getId())
                        .content("đã tạo phòng chat")
                        .notificationType(NotificationType.NEW_CHATROOM)
                        .createdBy(request.getCreatedBy())
                .build());

        // tạo đánh dấu người dùng đã đọc thông báo
        notificationAccountRepository.save(NotificationAccountEntity.builder()
                .accountId(request.getCreatedBy())
                .notificationId(savedNotification.getId())
                .build());

        return ChatRoomNotificationResponse.builder()
                .chatRoomId(chatRoom.getId())
                .roomName(createdByAccount.getId() + "|" + createdByAccount.getNickname() + "|" + otherAccount.getNickname())
                .roomAvatar(createdByAccount.getAvatar() + "|" + otherAccount.getAvatar())
                .chatRoomType(chatRoom.getChatRoomType())
//                .status(Status.ONLINE)
                .notificationId(savedNotification.getId())
                .content(savedNotification.getContent())
                .notificationType(savedNotification.getNotificationType())
                .isRead(false)
                .lastModifiedDate(LocalDateTime.ofInstant(savedNotification.getLastModifiedDate(), ZoneId.systemDefault()))
                .build();
    }

    @Override
    public ChatRoomNotificationResponse createGroupChatRoom(CreateGroupChatRoomRequest request) {
        // Kiểm tra xem phòng chat đã tồn tại chưa
        List<String> memberIds = new ArrayList<>(List.of(request.getMemberIds().split("\\|")));
        memberIds.add(request.getCreatedBy());
        if (chatRoomExists(memberIds)) throw new AppException(ErrorCode.EXISTED_CHATROOM);
        String avatarUrl = request.getRoomAvatar() != null ? minioChannel.upload(request.getRoomAvatar()) : "http://localhost:9000/resources/default-avatar.jpg";
        // Thêm phòng mới
        ChatRoomEntity chatRoom = chatRoomRepository.save(ChatRoomEntity.builder()
                .roomName(request.getRoomName())
                .roomAvatar(avatarUrl)
                .createdBy(request.getCreatedBy())
                .numberOfMembers(memberIds.size())
                .chatRoomType(ChatRoomType.GROUP)
                .status(Status.OFFLINE)
                .build());

        AccountEntity createdByAccount = accountRepository.findById(request.getCreatedBy())
                .orElseThrow(() -> new AppException(ErrorCode.EXISTED_USER));

        // Thêm các thành viên vào phòng
        memberIds.forEach(memberId -> {
            AccountEntity account = accountRepository.findById(memberId)
                    .orElseThrow(() -> new AppException(ErrorCode.EXISTED_USER));
            chatRoomMemberRepository.save(ChatRoomMemberEntity.builder()
                    .chatRoomId(chatRoom.getId())
                    .accountId(memberId)
                    .nickname(account.getNickname())
                    .role(memberId.equals(request.getCreatedBy()) ? ChatRoomRole.ADMIN : ChatRoomRole.MEMBER)
                    .isActive(true)
                    .build());
        });

        // Tạo thông báo thêm mới phòng chat
        NotificationEntity savedNotification = notificationRepository.save(NotificationEntity.builder()
                .chatRoomId(chatRoom.getId())
                .content("đã tạo phòng chat")
                .notificationType(NotificationType.NEW_CHATROOM)
                .createdBy(request.getCreatedBy())
                .build());

        // tạo đánh dấu người dùng đã đọc thông báo
        notificationAccountRepository.save(NotificationAccountEntity.builder()
                .accountId(request.getCreatedBy())
                .notificationId(savedNotification.getId())
                .build());

        return ChatRoomNotificationResponse.builder()
                .chatRoomId(chatRoom.getId())
                .roomName(chatRoom.getRoomName())
                .roomAvatar(chatRoom.getRoomAvatar())
                .chatRoomType(chatRoom.getChatRoomType())
//                .status(Status.ONLINE)
                .notificationId(savedNotification.getId())
                .content(createdByAccount.getId() + "|" + createdByAccount.getNickname() + "|" + savedNotification.getContent())
                .notificationType(savedNotification.getNotificationType())
                .isRead(false)
                .lastModifiedDate(LocalDateTime.ofInstant(savedNotification.getLastModifiedDate(), ZoneId.systemDefault()))
                .build();
    }

    @Override
    public ChatRoomDto removeAccountFromChatRoom(RemoveAccountFromChatRoomRequest request) {
        return null;
    }

    @Override
    public List<ChatRoomNotificationResponse> findAllChatRoomNotificationsByUserId(String userId) {
        List<Object[]> results = chatRoomRepository.findAllChatRoomsNotificationByUserId(userId);
        return results.stream().map(ChatRoomNotificationMapper::mapToChatRoomNotificationResponse).toList();
    }

    @Override
    public List<String> findAllChatRoomsByUserId(String userId) {
        return chatRoomRepository.findAllChatRoomsByUserId(userId).stream().map(result -> (String) result[0]).toList();
    }

    @Override
    public ChatRoomDto updateChatRoom(String id, UpdateChatRoomRequest request) {
        return chatRoomMapper.toDto(chatRoomRepository.findById(id).map(chatroom -> {
            chatroom.setRoomAvatar(minioChannel.upload(request.getRoomAvatar()));
            return chatRoomRepository.save(chatroom);
        }).orElseThrow(() -> new RuntimeException("USER NOT EXIST"))
        );
    }

    public boolean chatRoomExists(List<String> memberIds) {
        List<ChatRoomEntity> existingChatRooms = chatRoomRepository.findChatRoomByMemberIds(memberIds, memberIds.size());
        return !existingChatRooms.isEmpty();
    }
}
