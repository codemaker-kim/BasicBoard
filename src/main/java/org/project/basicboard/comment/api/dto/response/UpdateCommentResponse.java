package org.project.basicboard.comment.api.dto.response;

import lombok.Builder;
import org.project.basicboard.comment.domain.Comment;

@Builder
public record UpdateCommentResponse(
        Long commentId,
        String content
) {
    public static UpdateCommentResponse from(Comment comment) {
        return UpdateCommentResponse.builder()
                .commentId(comment.getId())
                .content(comment.getContent())
                .build();
    }
}
