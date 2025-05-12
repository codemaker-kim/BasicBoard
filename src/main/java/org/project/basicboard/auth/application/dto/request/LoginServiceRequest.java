package org.project.basicboard.auth.application.dto.request;

import lombok.Builder;

@Builder
public record LoginServiceRequest(
        String username,
        String password
) {
}