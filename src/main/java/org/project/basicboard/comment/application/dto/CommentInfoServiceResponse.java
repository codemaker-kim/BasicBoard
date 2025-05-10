package org.project.basicboard.comment.application.dto;

public record CommentInfoServiceResponse(
        String writer,
        String content
) {
}