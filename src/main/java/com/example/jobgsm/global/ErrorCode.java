package com.example.jobgsm.global;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCode {

    BOARD_NOT_FOUND("게시글을 찾을 수 없습니다.",404),
    NOT_NULL("null이 아닌 값 또는 공백이 아닌 문자를 하나 이상 포함해야 합니다",400);

    private final String message;
    private final int status;
}
