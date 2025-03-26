package org.project.basicboard.article.api.dto;

import lombok.Builder;
import org.project.basicboard.article.domain.Article;

import java.time.LocalDateTime;

@Builder
public record ArticleDto(
        Long id,
        String title,
        String content,
        String writer,
        LocalDateTime published,
        Integer likeCount,
        Integer views
) {
    public static ArticleDto from(Article article) {
        return ArticleDto.builder()
                .id(article.getId())
                .title(article.getTitle())
                .content(article.getContent())
                .writer(article.getUser().getNickname())
                .published(article.getCreatedAt())
                .likeCount(article.getLikeCount())
                .build();
    }
}