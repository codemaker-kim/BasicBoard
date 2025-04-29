package org.project.basicboard.auth.application.dto.request;

import lombok.Builder;

@Builder
public record UserInfoDto(
        Long id,
        String username
) {
}