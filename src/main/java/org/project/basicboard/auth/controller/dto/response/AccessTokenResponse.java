package org.project.basicboard.auth.controller.dto.response;

import org.project.basicboard.auth.application.dto.response.AccessTokenServiceResponse;

public record AccessTokenResponse(
        String accessToken
) {
    public static AccessTokenResponse from(AccessTokenServiceResponse response) {
        return new AccessTokenResponse(response.accessToken());
    }
}