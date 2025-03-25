package org.project.basicboard.auth.api.dto.request;

import jakarta.validation.constraints.NotEmpty;

public record LoginRequest(
        @NotEmpty(message = "아이디가 비어있습니다.") String username,
        @NotEmpty(message = "비밀번호가 비어있습니다.") String password
) {
}
