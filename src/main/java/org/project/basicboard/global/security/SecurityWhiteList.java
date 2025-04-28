package org.project.basicboard.global.security;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum SecurityWhiteList {
    SWAGGER_ALLOW_URLS(new String[]{
            "/swagger",
            "/swagger-ui.html",
            "/swagger-ui/**",
            "/api-docs",
            "/api-docs/**",
            "/v3/api-docs/**"}),

    API_ALLOW_URLS(new String[]{
            "/api/users/join",
            "/api/auth/login",
            "/api/auth/token"
    });

    private final String[] endPoints;
}