package com.example.jobgsm.domain.user.exception;

import com.example.jobgsm.global.exception.ErrorCode;
import lombok.Getter;

@Getter
public class UserNotFoundException extends RuntimeException{

    private final ErrorCode errorCode;

    public UserNotFoundException(String message) {
        super(message);
        this.errorCode = ErrorCode.USER_NOT_FOUND;
    }
}
