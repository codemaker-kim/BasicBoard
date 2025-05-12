package org.project.basicboard.auth.application.dto.request;

public record AccessTokenServiceRequest(
        String refreshToken
) {
}