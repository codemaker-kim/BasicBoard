package org.project.basicboard.user.application.dto.request;

public record UserJoinServiceRequest(
        String username,
        String password,
        String nickname
) {
}
