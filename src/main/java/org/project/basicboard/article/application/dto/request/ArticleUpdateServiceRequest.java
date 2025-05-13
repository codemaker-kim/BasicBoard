package org.project.basicboard.article.application.dto.request;

import lombok.Builder;

@Builder
public record ArticleUpdateServiceRequest(
        String title,
        String content
) {
}