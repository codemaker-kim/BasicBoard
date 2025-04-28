package org.project.basicboard.user.controller.dto.request;

import jakarta.validation.constraints.NotBlank;

public record UserJoinRequest(
        @NotBlank(message = "아이디가 비어있습니다.") String username,
        @NotBlank(message = "패스워드가 비어있습니다.") String password,
        @NotBlank(message = "닉네임이 비어있습니다.") String nickname
) {
}
