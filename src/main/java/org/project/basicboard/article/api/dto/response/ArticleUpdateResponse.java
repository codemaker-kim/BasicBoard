package org.project.basicboard.article.api.dto.response;

import lombok.Builder;
import org.project.basicboard.article.domain.Article;

@Builder
public record ArticleUpdateResponse(
        Long articleId,
        String title,
        String content
) {
    public static ArticleUpdateResponse from(Article article) {
        return ArticleUpdateResponse.builder()
                .articleId(article.getId())
                .title(article.getTitle())
                .content(article.getContent())
                .build();
    }
}
