package org.project.basicboard.global.jwt;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum TokenUtil {
    JWT_PREFIX("Bearer "),
    AUTH_HEADER("Authorization");

    final String value;
}
