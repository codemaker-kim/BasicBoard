package org.project.basicboard.comment.api.dto.response;

import lombok.Builder;
import org.project.basicboard.comment.domain.Comment;

@Builder
public record AddCommentResponse(
        Long commentId,
        String content
) {
    public static AddCommentResponse from(Comment comment) {
        return AddCommentResponse.builder()
                .commentId(comment.getId())
                .content(comment.getContent())
                .build();
    }
}
