package com.example.jobgsm.domain.auth.exception;


import com.example.jobgsm.global.exception.ErrorCode;
import lombok.Getter;

@Getter
public class MisMatchPasswordException extends RuntimeException{
    private final ErrorCode errorCode;

    public MisMatchPasswordException(String message) {
        super(message);
        this.errorCode = ErrorCode.NOTMATCH_MEMBER_PASSWORD;
    }
}
