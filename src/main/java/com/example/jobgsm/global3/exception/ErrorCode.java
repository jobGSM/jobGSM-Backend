package com.example.jobgsm.global3.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCode {
    MEMBER_NOT_FOUND("존재하지 않는 Id 입니다.", 404),
    NOTMATCH_MEMBER_PASSWORD("비밀번호가 일치하지 않습니다.", 400),

    CODE_EXPIRED("인증번호가 만료되었습니다.",409),
    ALREADY_EXIST_EMAIL("이미 존재하는 이메일입니다." , 400),

    NOTMATCH_AUTHCODE("인증번호가 일치하지 않습니다.",409),
    REFRESH_TOKEN_NOT_FOUND("존재하지 않는 리프레시 토큰입니다.", 404);



    private final String errorMessage;
    private final int status;

}
