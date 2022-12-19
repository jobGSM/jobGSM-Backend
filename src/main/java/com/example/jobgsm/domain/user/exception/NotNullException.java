package com.example.jobgsm.domain.user.exception;

import com.example.jobgsm.global.exception.ErrorCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class NotNullException extends RuntimeException{

    private final ErrorCode errorCode;

    public NotNullException(String message){
        super(message);
        this.errorCode = ErrorCode.NOT_NULL;
    }
}
