package org.project.basicboard.global.error.dto;

public record ErrorResponse(
        Integer errorCode,
        String message
) {
}
