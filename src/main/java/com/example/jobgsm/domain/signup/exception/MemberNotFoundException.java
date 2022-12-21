package com.example.jobgsm.domain.signup.exception;


import lombok.Getter;

@Getter
public class MemberNotFoundException extends RuntimeException{

    private final ErrorCode error;

    public MemberNotFoundException(String message) {
        super(message);
        this.error = ErrorCode.MEMBER_NOT_FOUND;
    }
}