package org.project.basicboard.comment.application.dto.request;

import lombok.Builder;

@Builder
public record UpdateCommentServiceRequest(
        Long id,
        String content,
        String username
) {
}