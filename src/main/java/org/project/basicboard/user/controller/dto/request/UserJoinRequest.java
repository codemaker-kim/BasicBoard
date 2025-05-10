package org.project.basicboard.user.controller.dto.request;

import jakarta.validation.constraints.NotBlank;
import org.project.basicboard.user.application.dto.request.UserJoinServiceRequest;

public record UserJoinRequest(
        @NotBlank(message = "아이디가 비어있습니다.") String username,
        @NotBlank(message = "패스워드가 비어있습니다.") String password,
        @NotBlank(message = "닉네임이 비어있습니다.") String nickname
) {
    public UserJoinServiceRequest toServiceRequest() {
        return UserJoinServiceRequest.builder()
                .username(username)
                .password(password)
                .nickname(nickname)
                .build();
    }
}
