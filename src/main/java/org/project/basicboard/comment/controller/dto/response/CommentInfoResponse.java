package org.project.basicboard.comment.controller.dto.response;

import lombok.Builder;
import org.project.basicboard.comment.application.dto.CommentInfoServiceResponse;

@Builder
public record CommentInfoResponse(
        String writer,
        String content
) {
    public static CommentInfoResponse from(CommentInfoServiceResponse response) {
        return CommentInfoResponse.builder()
                .writer(response.writer())
                .content(response.content())
                .build();
    }
}