package org.project.basicboard.article.controller.dto.response;

import lombok.Builder;
import org.project.basicboard.article.application.dto.response.ArticleServiceResponse;
import org.project.basicboard.article.domain.Article;
import org.project.basicboard.comment.controller.dto.response.CommentDetailResponse;

import java.time.LocalDateTime;
import java.util.List;

@Builder
public record ArticleResponse(
        Long id,
        String title,
        String content,
        String author,
        LocalDateTime createdAt,
        Long likeCount,
        Integer views,
        boolean like,
        boolean bookmarked,
        List<CommentDetailResponse> comments
) {
    public static ArticleResponse from(ArticleServiceResponse response) {
        return ArticleResponse.builder()
                .id(response.id())
                .title(response.title())
                .content(response.content())
                .author(response.author())
                .createdAt(response.createdAt())
                .likeCount(response.likeCount())
                .views(response.views())
                .like(response.like())
                .bookmarked(response.bookmarked())
                .comments(response.comments().stream()
                        .map(CommentDetailResponse::from)
                        .toList())
                .build();
    }
}