package org.project.basicboard.auth.api.dto.request;

import jakarta.validation.constraints.NotBlank;

public record NicknameUpdateRequest(
        @NotBlank(message = "닉네임이 비어있습니다.") String nickname
) {
}
