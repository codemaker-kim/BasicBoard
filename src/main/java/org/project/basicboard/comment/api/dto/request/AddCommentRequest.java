package org.project.basicboard.comment.api.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

public record AddCommentRequest(
        @Positive(message = "게시글 id는 양수여야 합니다.") Long articleId,
        @NotBlank(message = "댓글 내용이 비어있습니다.") String content
) {
}
