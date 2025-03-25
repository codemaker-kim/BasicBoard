package org.project.basicboard.auth.api.dto.request;

import jakarta.validation.constraints.NotEmpty;

public record CreateAccessTokenRequest(
        @NotEmpty(message = "토큰이 비어있습니다.") String refreshToken
) {
}
