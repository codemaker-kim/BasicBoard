package org.project.basicboard.auth.api.dto.response;

import lombok.Builder;

@Builder
public record LoginResponse(
        String refreshToken,
        String accessToken
) {
}