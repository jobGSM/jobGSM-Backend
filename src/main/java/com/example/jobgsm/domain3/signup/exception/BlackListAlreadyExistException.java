package com.example.jobgsm.domain3.signup.exception;

import com.example.jobgsm.global3.exception.ErrorCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class BlackListAlreadyExistException extends RuntimeException{
    private final ErrorCode errorCode;

    public BlackListAlreadyExistException(String message){
        super(message);
        this.errorCode = ErrorCode.BLACK_LIST_ALREADY_EXIST;
    }
}