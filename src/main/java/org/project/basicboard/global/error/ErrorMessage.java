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
    NOT_AUTHORIZE_NICKNAME_UPDATE("닉네임 업데이트 권한이 없습니다."),

    INVALID_TOKEN("잘못된 토큰입니다."),
    EMPTY_CLAIMS("클레임이 비어있습니다."),
    REFRESH_TOKEN_NOT_FOUND("존재하지 않는 리프레시 토큰입니다."),
    NO_AUTHENTICATION_INFO("인증된 사용자 정보가 존재하지 않습니다."),

    ARTICLE_NOT_FOUND("게시글을 찾을 수 없습니다."),
    NOT_AUTHORIZE_ARTICLE_DELETE("게시글 삭제 권한이 없습니다.");

    private final String message;
}
