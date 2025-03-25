package org.project.basicboard.global.error;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum ErrorMessage {
    USER_ALREADY_EXISTS("이미 존재하는 아이디입니다."),
    NICKNAME_ALREADY_EXISTS("이미 존재하는 닉네임입니다."),
    USER_NOT_FOUND("사용자를 찾을 수 없습니다."),
    WRONG_PASSWORD("비밀번호가 잘못되었습니다."),

    INVALID_TOKEN("잘못된 토큰입니다."),
    EMPTY_CLAIMS("클레임이 비어있습니다.");

    private final String message;
}
