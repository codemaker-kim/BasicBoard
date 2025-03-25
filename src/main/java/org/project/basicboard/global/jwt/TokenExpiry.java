package org.project.basicboard.global.jwt;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum TokenExpiry {
    ACCESS_TOKEN_EXPIRY_TIME(60 * 60 * 1000L),
    REFRESH_TOKEN_EXPIRY_TIME(14 * 24 * 60 * 60 * 1000L);

    final Long expiry;
}
