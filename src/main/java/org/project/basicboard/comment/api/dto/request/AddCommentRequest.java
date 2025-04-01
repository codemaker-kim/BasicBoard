package org.project.basicboard.comment.api.dto.request;

import jakarta.validation.constraints.NotBlank;

public record AddCommentRequest(
        @NotBlank(message = "게시글 id가 비어있습니다.") Long articleId,
        @NotBlank(message = "댓글 내용이 비어있습니다.") String content
) {
}
