package com.hoanvan.messenger_v1.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;

@Getter
public enum ErrorCode {
    UNCATEGORIZED_EXCEPTION(9999, "Uncategorized error", HttpStatus.INTERNAL_SERVER_ERROR),
    INVALID_KEY(1001, "Uncategorized error", HttpStatus.BAD_REQUEST),
    EXISTED_USER(1002, "User existed", HttpStatus.CONFLICT),
    INVALID_USERNAME(1003, "Username must be at least {min} characters", HttpStatus.BAD_REQUEST),
    INVALID_LENGTH_PASSWORD(1004, "Password must be at least {min} characters", HttpStatus.BAD_REQUEST),
    NOT_EXISTED_USER(1005, "User not existed", HttpStatus.NOT_FOUND),
    UNAUTHENTICATED(1006, "Unauthenticated", HttpStatus.UNAUTHORIZED),
    UNAUTHORIZED(1007, "You do not have permission", HttpStatus.FORBIDDEN),
    INVALID_DOB(1008, "Your age must be at least {min}", HttpStatus.BAD_REQUEST),
    INVALID_PASSWORD(1009, "Password must contain at least one uppercase letter, one lowercase letter, and one number", HttpStatus.BAD_REQUEST),
    NOT_BLANK_USERNAME(1010, "Username not blank", HttpStatus.BAD_REQUEST),
    NOT_BLANK_PASSWORD(1011, "Password not blank", HttpStatus.BAD_REQUEST),
    NOT_BLANK_REPASSWORD(1012, "RePassword not blank", HttpStatus.BAD_REQUEST),
    NOT_BLANK_NICKNAME(1013, "Nickname not blank", HttpStatus.BAD_REQUEST),
    WRONG_REPASSWORD(1014, "Passwords do not match", HttpStatus.BAD_REQUEST),
    NOT_NULL_DOB(1015, "DateOfBirth not null", HttpStatus.BAD_REQUEST),
    INVALID_USERNAME_PASSWORD(1016, "Username or password is incorrect", HttpStatus.UNAUTHORIZED),
    FAILED_TO_UPLOAD_FILE(1016, "Unable to upload file on minio", HttpStatus.BAD_REQUEST),
    CONVERTER_NOT_FOUND(1017, "Converter not found", HttpStatus.BAD_REQUEST),
    EXISTED_CHATROOM(1018, "Chatroom existed!", HttpStatus.CONFLICT)
    ;

    ErrorCode(int code, String message, HttpStatusCode statusCode) {
        this.code = code;
        this.message = message;
        this.statusCode = statusCode;
    }

    private final int code;
    private final String message;
    private final HttpStatusCode statusCode;
}
