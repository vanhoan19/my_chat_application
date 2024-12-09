package com.hoanvan.messenger_v1.service.imp;

import com.hoanvan.messenger_v1.entity.*;
import com.hoanvan.messenger_v1.entity.enums.NotificationType;
import com.hoanvan.messenger_v1.integration.minio.MinioChannel;
import com.hoanvan.messenger_v1.repository.*;
import com.hoanvan.messenger_v1.service.MessageService;
import com.hoanvan.messenger_v1.service.dto.request.SendMessageRequest;
import com.hoanvan.messenger_v1.service.dto.response.ChatRoomMessagesResponse;
import com.hoanvan.messenger_v1.service.mapper.MessageMapper;
import com.hoanvan.messenger_v1.service.mapper.custom.ChatRoomMessageMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Slf4j
@Service
@RequiredArgsConstructor
public class MessageServiceImp implements MessageService {
    private final MessageRepository messageRepository;
    private final AttachmentRepository attachmentRepository;
    private final NotificationRepository notificationRepository;
    private final NotificationAccountRepository notificationAccountRepository;
    private final ChatRoomMemberRepository chatRoomMemberRepository;
    private final ChatRoomRepository chatRoomRepository;
    private final MessageMapper messageMapper;
    private final MinioChannel minioChannel;

    @Override
    public ChatRoomMessagesResponse saveMessage(SendMessageRequest request) {
        String chatRoomId = request.getChatRoomId();

        // luu tin nhan vao db trc
        MessageEntity messageEntity = messageRepository.save(
                MessageEntity.builder()
                        .chatRoomId(chatRoomId)
                        .senderId(request.getSenderId())
                        .content(request.getContent())
                        .build()
        );

        List<String> fileAttachments = new ArrayList<>();
        // luu cac file dinh kem
        request.getFileAttachments().forEach(fileAttachment -> {
            if (!Objects.equals(fileAttachment.getOriginalFilename(), "") && fileAttachment.getSize() > 0) {
                String fileUrl = minioChannel.upload(fileAttachment);
                attachmentRepository.save(AttachmentEntity.builder()
                        .messageId(messageEntity.getId())
                        .fileName(fileAttachment.getName())
                        .filePath(fileUrl)
                        .fileSize(fileAttachment.getSize() / (1024.0 * 1024.0))
                        .fileType(fileAttachment.getContentType())
                        .build());
                fileAttachments.add(fileUrl);
            }
        });

        // taoj thong bao tin nhan moi toi cac phong chat
        NotificationEntity notificationEntity = NotificationEntity.builder()
                        .chatRoomId(chatRoomId)
                        .messageId(messageEntity.getId())
                        .createdBy(request.getSenderId())
                        .notificationType(NotificationType.NEW_MESSAGE)
                .build();
        ChatRoomMemberEntity chatRoomMemberEntity = chatRoomMemberRepository.findByChatRoomIdAndAccountId(chatRoomId, request.getSenderId()).orElseThrow();

        if (!fileAttachments.isEmpty()) {
            notificationEntity.setContent(MessageFormat.format("đã gửi {0} file đính kèm", request.getFileAttachments().size()));
        }
        else {
            notificationEntity.setContent(request.getContent());
        }
        NotificationEntity notificationSaved = notificationRepository.save(notificationEntity);

        // tạo đánh dấu người dùng đã đọc thông báo
        notificationAccountRepository.save(NotificationAccountEntity.builder()
                        .accountId(request.getSenderId())
                        .notificationId(notificationEntity.getId())
                .build());

        return ChatRoomMessagesResponse.builder()
                .messageId(messageEntity.getId())
                .chatRoomId(chatRoomId)
                .senderId(request.getSenderId())
                .avatar(request.getAvatar())
                .username(request.getUsername())
                .nickname(chatRoomMemberEntity.getNickname())
                .notificationId(notificationSaved.getId())
                .contentNotification(notificationSaved.getContent())
                .contentMessage(request.getContent())
                .fileAttachments(fileAttachments)
                .lastModifiedDate(LocalDateTime.ofInstant((Instant) messageEntity.getLastModifiedDate(), ZoneId.systemDefault()))
                .build();
    }

    @Override
    public List<ChatRoomMessagesResponse> findAllMessageByChatRoomId(String chatRoomId) {
        List<Object[]> results = messageRepository.findMessagesByChatRoomId(chatRoomId);
        List<ChatRoomMessagesResponse> responses = new ArrayList<>();
        for (Object[] result : results) {
            ChatRoomMessagesResponse response = ChatRoomMessageMapper.mapToChatRoomMessagesResponse(result);
            responses.add(response);
        }
        return responses;
    }
}
