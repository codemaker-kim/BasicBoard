package org.project.basicboard.user.controller.dto.response;

import lombok.Builder;
import org.project.basicboard.user.application.dto.response.UserJoinServiceResponse;

@Builder
public record UserJoinResponse(
        Long id,
        String username
) {
    public static UserJoinResponse from(UserJoinServiceResponse userJoinServiceResponse) {
        return UserJoinResponse.builder()
                .id(userJoinServiceResponse.id())
                .username(userJoinServiceResponse.username())
                .build();
    }
}