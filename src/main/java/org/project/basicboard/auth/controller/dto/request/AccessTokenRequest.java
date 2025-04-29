package org.project.basicboard.auth.controller.dto.request;

import jakarta.validation.constraints.NotBlank;

public record CreateAccessTokenRequest(
        @NotBlank(message = "액세스 토큰이 비어있습니다.") String accessToken,
        @NotBlank(message = "리프레시 토큰이 비어있습니다.") String refreshToken
) {
}
