package com.example.jobgsm.global;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCode {

    NOT_NULL("null값은 허용되지 않습니다.",400);

    private final String message;
    private final int status;
}
