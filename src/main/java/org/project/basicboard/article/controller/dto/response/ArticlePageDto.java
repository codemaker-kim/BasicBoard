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
    public static ArticlePageDto from(Article article) {
        return ArticlePageDto.builder()
                .id(article.getId())
                .title(article.getTitle())
                .createdAt(article.getCreatedAt())
                .views(article.getViews())
                .build();
    }
}