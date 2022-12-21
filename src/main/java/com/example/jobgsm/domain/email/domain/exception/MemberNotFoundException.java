package com.example.jobgsm.domain.email.domain.exception;

import com.example.jobgsm.domain.email.global.exception.ErrorCode;
import lombok.Getter;

@Getter
public class MemberNotFoundException extends RuntimeException{
    private final ErrorCode errorCode;

    public MemberNotFoundException(String message) {
        super(message);
        this.errorCode = ErrorCode.EXPIRE_EMAIL_CODE;
    }
}
