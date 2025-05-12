package org.project.basicboard.article.controller.dto.response;

import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record ArticlePageResponse(
        Long id,
        String title,
        LocalDateTime createdAt,
        Integer views
) {
}