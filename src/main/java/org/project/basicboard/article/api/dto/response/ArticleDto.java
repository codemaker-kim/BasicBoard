package org.project.basicboard.article.api.dto.response;

import lombok.Builder;
import org.project.basicboard.article.domain.Article;

import java.time.LocalDateTime;

@Builder
public record ArticleDto(
        Long id,
        String title,
        String content,
        String author,
        LocalDateTime published,
        Integer likeCount,
        Integer views
) {
    public static ArticleDto from(Article article) {
        return ArticleDto.builder()
                .id(article.getId())
                .title(article.getTitle())
                .content(article.getContent())
                .author(article.getAuthor())
                .published(article.getCreatedAt())
                .likeCount(article.getLikeCount())
                .build();
    }
}