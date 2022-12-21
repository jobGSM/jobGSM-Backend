package com.example.jobgsm.domain.signup.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCode {
    MEMBER_NOT_FOUND("존재하지 않는 Id 입니다.", 404),
    NOTMATCH_MEMBER_PASSWORD("비밀번호가 일치하지 않습니다.", 400),
    CODE_EXPIRED("인증번호가 만료되었습니다.",409),
    NOTMATCH_AUTHCODE("인증번호가 일치하지 않습니다.",409);


    private final String errorMessage;
    private final int status;

}
