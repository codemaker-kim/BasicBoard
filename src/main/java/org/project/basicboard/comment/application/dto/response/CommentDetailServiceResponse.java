package org.project.basicboard.comment.application.dto.response;

import lombok.Builder;
import org.project.basicboard.comment.domain.Comment;

@Builder
public record CommentDetailServiceResponse(
        String writer,
        String content
) {
    public static CommentDetailServiceResponse from(Comment comment) {
        return CommentDetailServiceResponse.builder()
                .writer(comment.getWriter())
                .content(comment.getContent())
                .build();
    }
}