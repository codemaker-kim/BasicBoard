package org.project.basicboard.auth.api.dto.request;

import jakarta.validation.constraints.NotBlank;

public record CreateAccessTokenRequest(
        @NotBlank(message = "토큰이 비어있습니다.") String refreshToken
) {
}
