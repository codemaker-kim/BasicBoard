package org.project.basicboard.comment.api.dto.request;

import jakarta.validation.constraints.NotEmpty;

public record UpdateCommentRequest(
        @NotEmpty(message = "업데이트할 댓글 내용이 비어있습니다.") String content
) {
}
