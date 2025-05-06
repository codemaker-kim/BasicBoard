package org.project.basicboard.comment.controller.dto.response;

import lombok.Builder;

@Builder
public record CommentInfoResponse(
        String writer,
        String content
) {
}
