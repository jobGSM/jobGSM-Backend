package com.example.jobgsm.global.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCode {

    USER_NOT_FOUND("유저를 찾을 수 없습니다.",404),
    NOT_NULL("null값은 허용되지 않습니다.", 400),
    PASSWORD_WRONG("비밀번호가 올바르지 않습니다.", 400),
    MEMBER_NOT_FOUND("존재하지 않는 Id 입니다.", 404),
    NOTMATCH_MEMBER_PASSWORD("비밀번호가 일치하지 않습니다.", 400),

    CODE_EXPIRED("인증번호가 만료되었습니다.",409),
    ALREADY_EXIST_EMAIL("이미 존재하는 이메일입니다." , 400),

    NOTMATCH_AUTHCODE("인증번호가 일치하지 않습니다.",409),
    BLACK_LIST_ALREADY_EXIST("블랙리스트에 이미 존재합니다.",409),
    TOKEN_EXPIRATION("토큰이 만료 되었습니다.", 401),
    TOKEN_NOT_VALID("토큰이 유효 하지 않습니다.", 401),

    REFRESH_TOKEN_NOT_FOUND("존재하지 않는 리프레시 토큰입니다.", 404),
    MANY_REQUEST_EMAIL_AUTH("15분에 최대 3번의 이메일 요청만 가능합니다." , 429),
    EXPIRE_EMAIL_CODE("이메일 인증번호 시간이 만료되었습니다.", 401),
    MISMATCH_AUTH_CODE("인증번호가 일치하지 않습니다." , 400);
    private final String message;
    private final int status;
}
