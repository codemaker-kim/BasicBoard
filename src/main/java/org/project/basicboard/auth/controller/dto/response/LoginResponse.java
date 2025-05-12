package org.project.basicboard.auth.controller.dto.response;

import lombok.Builder;
import org.project.basicboard.auth.application.dto.response.LoginServiceResponse;

@Builder
public record LoginResponse(
        String accessToken,
        String refreshToken
) {
    public static LoginResponse from(LoginServiceResponse response) {
        return LoginResponse.builder()
                .accessToken(response.accessToken())
                .refreshToken(response.refreshToken())
                .build();
    }
}