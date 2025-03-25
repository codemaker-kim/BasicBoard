package org.project.basicboard.user.api.dto.response;

import lombok.Builder;
import org.project.basicboard.user.domain.User;

@Builder
public record UserJoinResponse(
        Long id,
        String username
) {
    public static UserJoinResponse from(User user) {
        return UserJoinResponse.builder()
                .id(user.getId())
                .username(user.getUsername())
                .build();
    }
}
