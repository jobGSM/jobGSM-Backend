package com.example.jobgsm.domain.signup.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCode {
    EXIST_MEMBER_ID("이미 존재하는 Id 입니다", 404);

    private final String errorMessage;
    private final int status;

}
