package org.project.basicboard.global.error.dto;

public record ParamErrorResponse(
        int statusCode,
        String message
) {
}
