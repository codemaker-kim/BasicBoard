package org.project.basicboard.global.error.dto;

public record ErrorResponse(
        int statusCode,
        String message
) {
}