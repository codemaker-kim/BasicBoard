package org.project.basicboard.auth.api.dto;

import lombok.Builder;
import org.project.basicboard.user.domain.User;

@Builder
public record UserInfoDto(
        Long userId,
        String username
) {
    public static UserInfoDto from(User user) {
        return UserInfoDto.builder()
                .userId(user.getId())
                .username(user.getUsername())
                .build();
    }
}