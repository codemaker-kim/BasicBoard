package org.project.basicboard.global.error;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ErrorMessage {
    USER_ALREADY_EXISTS("이미 존재하는 아이디입니다.", HttpStatus.BAD_REQUEST),
    NICKNAME_ALREADY_EXISTS("이미 존재하는 닉네임입니다.", HttpStatus.BAD_REQUEST),
    USER_NOT_FOUND("사용자를 찾을 수 없습니다.", HttpStatus.NOT_FOUND),
    WRONG_PASSWORD("비밀번호가 잘못되었습니다.", HttpStatus.BAD_REQUEST),
    NO_USER_MATCH_REFRESH_TOKEN("리프레시 토큰과 일치하는 사용자가 없습니다.", HttpStatus.NOT_FOUND),
    LOGOUT_USER_NOT_FOUND("로그아웃하려는 사용자가 없습니다.", HttpStatus.NOT_FOUND),

    INVALID_TOKEN("잘못된 토큰입니다.", HttpStatus.BAD_REQUEST),
    EMPTY_CLAIMS("클레임이 비어있습니다.", HttpStatus.BAD_REQUEST),
    REFRESH_TOKEN_NOT_FOUND("존재하지 않는 리프레시 토큰입니다.", HttpStatus.NOT_FOUND),
    NO_AUTHENTICATION_INFO("인증된 사용자 정보가 존재하지 않습니다.", HttpStatus.NOT_FOUND),

    ARTICLE_NOT_FOUND("게시글을 찾을 수 없습니다.", HttpStatus.NOT_FOUND),
    NOT_AUTHORIZE_ARTICLE("게시글 조작 권한이 없습니다.", HttpStatus.FORBIDDEN),

    COMMENT_NOT_FOUND("댓글을 찾을 수 없습니다.", HttpStatus.NOT_FOUND),
    NOT_AUTHORIZE_COMMENT("댓글 조작 권한이 없습니다.", HttpStatus.FORBIDDEN);

    private final String message;
    private final HttpStatus status;
}