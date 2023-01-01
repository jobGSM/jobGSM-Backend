package com.example.jobgsm.domain.application.exception;

import com.example.jobgsm.global.exception.ErrorCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class AlreadyApplicationException extends RuntimeException{

    private final ErrorCode errorCode;

    public AlreadyApplicationException(String message) {
        super(message);
        this.errorCode = ErrorCode.ALREADY_APPLICATION;
    }
}
