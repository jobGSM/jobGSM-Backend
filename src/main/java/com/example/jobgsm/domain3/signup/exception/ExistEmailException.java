package com.example.jobgsm.domain3.signup.exception;

import com.example.jobgsm.global3.exception.ErrorCode;
import lombok.Getter;

@Getter
public class ExistEmailException extends RuntimeException {

    private final ErrorCode errorCode;

    public ExistEmailException(String message) {
        super(message);
        this.errorCode = ErrorCode.ALREADY_EXIST_EMAIL;
    }
}