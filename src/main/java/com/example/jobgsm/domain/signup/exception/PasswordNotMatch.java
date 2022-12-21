package com.example.jobgsm.domain.signup.exception;


import lombok.Getter;

@Getter
public class PasswordNotMatch extends RuntimeException{
    private final ErrorCode error;

    public PasswordNotMatch(String message) {
        super(message);
        this.error = ErrorCode.NOTMATCH_MEMBER_PASSWORD;
    }
}
