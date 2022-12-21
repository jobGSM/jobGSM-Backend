package com.example.jobgsm.domain.signup.exception;

import lombok.Getter;

@Getter
public class NotMatchAuthCodeException extends RuntimeException{
    private final ErrorCode errorCode;

    public NotMatchAuthCodeException(String message){
        super(message);
        this.errorCode = ErrorCode.NOTMATCH_AUTHCODE;
    }
}