package org.project.basicboard.article.api.dto;

import lombok.Builder;
import org.project.basicboard.article.domain.Article;

import java.time.LocalDateTime;

@Builder
public record ArticlePageDto(
        Long id,
        String title,
        LocalDateTime published,
        Integer views
) {
    public static ArticlePageDto from(Article article) {
        return ArticlePageDto.builder()
                .id(article.getId())
                .title(article.getTitle())
                .published(article.getCreatedAt())
                .views(article.getViews())
                .build();
    }
}