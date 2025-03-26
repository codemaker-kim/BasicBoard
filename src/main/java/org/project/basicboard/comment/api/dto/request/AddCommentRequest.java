package org.project.basicboard.comment.api.dto.request;

import jakarta.validation.constraints.NotEmpty;

public record AddCommentRequest(
        @NotEmpty(message = "게시글 id가 비어있습니다.") Long articleId,
        @NotEmpty(message = "댓글 내용이 비어있습니다.") String content
) {
}
