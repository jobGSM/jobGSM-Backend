package com.example.jobgsm.global.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCode {

    USER_NOT_FOUND("유저를 찾을 수 없습니다.",404),
    NOT_NULL("null값은 허용되지 않습니다.", 400);

    private final String message;
    private final int status;
}
