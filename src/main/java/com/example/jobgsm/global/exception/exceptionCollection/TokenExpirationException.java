package com.example.jobgsm.global.exception.exceptionCollection;

import com.example.jobgsm.global.exception.ErrorCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class TokenExpirationException extends RuntimeException {
    private final ErrorCode errorCode;
    public TokenExpirationException(String message) {
        super(message);
        errorCode = ErrorCode.TOKEN_EXPIRATION;
    }
}