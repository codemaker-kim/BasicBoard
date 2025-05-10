package org.project.basicboard.user.application.dto.request;

import lombok.Builder;

@Builder
public record UserJoinServiceRequest(
        String username,
        String password,
        String nickname
) {
}
