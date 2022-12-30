package com.example.jobgsm.domain.application.exception;

import com.example.jobgsm.global.exception.ErrorCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class FullUpException extends RuntimeException{

    private final ErrorCode errorCode;

    public FullUpException(String message) {
        super(message);
        this.errorCode = ErrorCode.APPLICANTS_FULL_UP;
    }
}
