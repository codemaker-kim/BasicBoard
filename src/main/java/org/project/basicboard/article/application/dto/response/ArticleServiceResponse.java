package org.project.basicboard.article.application.dto.response;

import lombok.Builder;
import org.project.basicboard.article.repository.dto.response.ArticleProjectionResponse;
import org.project.basicboard.comment.application.dto.response.CommentDetailServiceResponse;

import java.time.LocalDateTime;
import java.util.List;

@Builder
public record ArticleServiceResponse(
        Long id,
        String title,
        String content,
        String author,
        LocalDateTime createdAt,
        Long likeCount,
        Integer views,
        boolean like,
        boolean bookmarked,
        List<CommentDetailServiceResponse> comments
) {
    public static ArticleServiceResponse of(ArticleProjectionResponse response, List<CommentDetailServiceResponse> commentList) {
        return ArticleServiceResponse.builder()
                .id(response.id())
                .title(response.title())
                .content(response.content())
                .author(response.author())
                .createdAt(response.createdAt())
                .likeCount(response.likeCount())
                .views(response.views())
                .like(response.like())
                .bookmarked(response.bookmarked())
                .comments(commentList)
                .build();
    }
}