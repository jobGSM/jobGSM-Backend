package com.example.jobgsm.domain.email.exception;

import com.example.jobgsm.global.exception.ErrorCode;
import lombok.Getter;

@Getter
public class MisMatchAuthCodeException extends RuntimeException{

    private final ErrorCode errorCode;

    public MisMatchAuthCodeException(String message) {
        super(message);
        this.errorCode = ErrorCode.MISMATCH_AUTH_CODE;
    }
}
