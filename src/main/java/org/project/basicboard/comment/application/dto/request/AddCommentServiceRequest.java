package org.project.basicboard.comment.application.dto.request;

import lombok.Builder;

@Builder
public record AddCommentServiceRequest(
        Long articleId,
        String content,
        String username
) {
}