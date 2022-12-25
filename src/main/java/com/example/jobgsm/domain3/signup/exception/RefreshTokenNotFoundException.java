package com.example.jobgsm.domain3.signup.exception;

import com.example.jobgsm.global3.exception.ErrorCode;
import lombok.Getter;

@Getter
public class RefreshTokenNotFoundException extends RuntimeException {
    private final ErrorCode errorCode;

    public RefreshTokenNotFoundException(String message) {
        super(message);
        this.errorCode = ErrorCode.REFRESH_TOKEN_NOT_FOUND;
    }

}