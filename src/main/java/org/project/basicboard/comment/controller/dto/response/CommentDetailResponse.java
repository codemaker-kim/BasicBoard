package org.project.basicboard.comment.controller.dto.response;

import lombok.Builder;
import org.project.basicboard.comment.application.dto.response.CommentDetailServiceResponse;

@Builder
public record CommentDetailResponse(
        String writer,
        String content
) {
    public static CommentDetailResponse from(CommentDetailServiceResponse response) {
        return CommentDetailResponse.builder()
                .writer(response.writer())
                .content(response.content())
                .build();
    }
}