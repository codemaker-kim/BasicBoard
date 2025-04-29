package org.project.basicboard.auth.application.dto.response;

public record LoginServiceResponse(
        String accessToken,
        String refreshToken
) {
}
