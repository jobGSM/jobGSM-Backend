package com.example.jobgsm.domain.signup.exception;

import lombok.Getter;

@Getter
public class MemberExistException extends RuntimeException{

    private final ErrorCode error;

    public MemberExistException(String message) {
        super(message);
        this.error = ErrorCode.EXIST_MEMBER_ID;
    }
}
