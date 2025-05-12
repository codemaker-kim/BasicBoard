package org.project.basicboard.auth.application.dto.request;

import lombok.Builder;

@Builder
public record UserInfoRequest(
        Long id,
        String username
) {
}