package org.project.basicboard.article.application.dto.response;

import org.project.basicboard.comment.controller.dto.response.CommentDetailResponse;

import java.time.LocalDateTime;
import java.util.List;

public record ArticleServiceResponse(
        Long id,
        String title,
        String content,
        String author,
        LocalDateTime createdAt,
        Integer likeCount,
        Integer views,
        boolean like,
        boolean bookmarked,
        List<CommentDetailResponse> comments
) {
}