package org.project.basicboard.auth.controller.dto.request;

import jakarta.validation.constraints.NotBlank;
import org.project.basicboard.auth.application.dto.request.AccessTokenServiceRequest;

public record AccessTokenRequest(
        @NotBlank(message = "리프레시 토큰이 비어있습니다.") String refreshToken
) {
    public AccessTokenServiceRequest toServiceRequest() {
        return new AccessTokenServiceRequest(refreshToken);
    }
}