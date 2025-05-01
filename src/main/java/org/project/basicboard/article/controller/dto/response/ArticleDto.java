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
        LocalDateTime createdAt,
        Integer likeCount,
        Integer views,
        boolean like,
        boolean bookmarked,
        List<CommentInfoDto> comments
) {
}