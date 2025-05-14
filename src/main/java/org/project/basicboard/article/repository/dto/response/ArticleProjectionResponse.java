package org.project.basicboard.article.repository.dto.response;

import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record ArticleProjectionResponse(
        Long id,
        String title,
        String content,
        String author,
        LocalDateTime createdAt,
        Long likeCount,
        Integer views,
        Boolean like,
        Boolean bookmarked
) {
}