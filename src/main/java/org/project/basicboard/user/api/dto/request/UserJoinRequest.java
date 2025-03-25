package org.project.basicboard.user.api.dto.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

@Builder
public record UserJoinRequest(
        @NotEmpty String username,
        @NotEmpty String password,
        @NotEmpty String nickname
) {
}
