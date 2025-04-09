package org.project.basicboard.comment.api.dto.response;

import lombok.Builder;
import org.project.basicboard.comment.domain.Comment;

@Builder
public record CommentResponse(
        Long commentId,
        String content
) {
    public static CommentResponse from(Comment comment) {
        return CommentResponse.builder()
                .commentId(comment.getId())
                .content(comment.getContent())
                .build();
    }
}