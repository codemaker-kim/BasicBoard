package org.project.basicboard.article.controller.dto.response;

import lombok.Builder;
import org.project.basicboard.comment.controller.dto.response.CommentInfoResponse;

import java.time.LocalDateTime;
import java.util.List;

@Builder
public record ArticleResponse(
        Long id,
        String title,
        String content,
        String author,
        LocalDateTime createdAt,
        Integer likeCount,
        Integer views,
        boolean like,
        boolean bookmarked,
        List<CommentInfoResponse> comments
) {
}