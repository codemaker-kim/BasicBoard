package org.project.basicboard.comment.controller.dto.request;

import jakarta.validation.constraints.NotBlank;

public record AddCommentRequest(
        @NotBlank(message = "댓글 내용이 비어있습니다.") String content
) {
}
