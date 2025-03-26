package org.project.basicboard.article.api.dto.response;

import lombok.Builder;

@Builder
public record ArticleUpdateResponse(
        Long articleId,
        String title,
        String content
) {
}
