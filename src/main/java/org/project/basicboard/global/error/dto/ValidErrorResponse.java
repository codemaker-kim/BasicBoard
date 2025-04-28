package org.project.basicboard.global.error.dto;

import java.util.List;

public record ValidErrorResponse(
        int statusCode,
        List<ValidErrorDetails> details
) {
}
