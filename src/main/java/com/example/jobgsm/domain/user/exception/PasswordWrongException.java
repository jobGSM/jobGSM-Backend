package com.example.jobgsm.domain.user.exception;

import com.example.jobgsm.global.exception.ErrorCode;
import lombok.Getter;

@Getter
public class PasswordWrongException extends RuntimeException{
    private final ErrorCode errorCode;

    public PasswordWrongException(String message) {
        super(message);
        this.errorCode = ErrorCode.NOTMATCH_MEMBER_PASSWORD;
    }
}
