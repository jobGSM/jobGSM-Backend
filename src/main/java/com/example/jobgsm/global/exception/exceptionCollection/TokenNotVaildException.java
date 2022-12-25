package com.example.jobgsm.global.exception.exceptionCollection;


import com.example.jobgsm.global.exception.ErrorCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class TokenNotVaildException extends RuntimeException {

    private final ErrorCode errorCode;

    public TokenNotVaildException(String message) {
        super(message);
        this.errorCode = ErrorCode.TOKEN_NOT_VALID;
    }
}