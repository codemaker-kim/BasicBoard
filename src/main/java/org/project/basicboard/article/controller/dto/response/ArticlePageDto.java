package org.project.basicboard.article.controller.dto.response;

import lombok.Builder;
import org.project.basicboard.article.domain.Article;

import java.time.LocalDateTime;

@Builder
public record ArticlePageDto(
        Long id,
        String title,
        LocalDateTime createdAt,
        Integer views
) {
}