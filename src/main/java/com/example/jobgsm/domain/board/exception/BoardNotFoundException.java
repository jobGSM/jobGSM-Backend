package com.example.jobgsm.domain.board.exception;

import com.example.jobgsm.global.exception.ErrorCode;

public class BoardNotFoundException extends RuntimeException{

    private final ErrorCode errorCode;

    public BoardNotFoundException(String message) {
        super(message);
        this.errorCode = ErrorCode.POSTS_NOT_FOUND;
    }
}
