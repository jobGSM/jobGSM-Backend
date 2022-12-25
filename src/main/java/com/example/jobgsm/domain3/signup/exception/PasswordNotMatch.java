package com.example.jobgsm.domain3.signup.exception;


import com.example.jobgsm.global3.exception.ErrorCode;
import lombok.Getter;

@Getter
public class PasswordNotMatch extends RuntimeException{
    private final ErrorCode errorCode;

    public PasswordNotMatch(String message) {
        super(message);
        this.errorCode = ErrorCode.NOTMATCH_MEMBER_PASSWORD;
    }
}
