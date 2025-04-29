package org.project.basicboard.auth.controller.dto.response;

public record LoginResponse(
        String accessToken,
        String refreshToken
) {
}
