package org.project.basicboard.article.controller.dto.response;

import lombok.Builder;
import org.project.basicboard.article.domain.Article;
import org.project.basicboard.comment.api.dto.response.CommentInfoDto;

import java.time.LocalDateTime;
import java.util.List;

@Builder
public record ArticleDto(
        Long id,
        String title,
        String content,
        String author,
        LocalDateTime published,
        Integer likeCount,
        Integer views,
        boolean like,
        boolean bookmarked,
        List<CommentInfoDto> comments
) {
    public static ArticleDto from(Article article, List<CommentInfoDto> comments, LikeAndBookmarkedDto likeAndBookmarked) {
        return ArticleDto.builder()
                .id(article.getId())
                .title(article.getTitle())
                .content(article.getContent())
                .author(article.getAuthor())
                .published(article.getCreatedAt())
                .likeCount(article.getLikeCount())
                .views(article.getViews())
                .like(likeAndBookmarked.like())
                .bookmarked(likeAndBookmarked.bookmarked())
                .comments(comments)
                .build();
    }
}