package org.project.basicboard.user.api.dto.request;

import jakarta.validation.constraints.NotEmpty;
import lombok.Builder;

@Builder
public record UserJoinRequest(
        @NotEmpty(message = "아이디가 비어있습니다.") String username,
        @NotEmpty(message = "패스워드가 비어있습니다. ") String password,
        @NotEmpty(message = "닉네임이 비어있습니다.") String nickname
) {
}
