package org.project.basicboard.article.application.dto.request;

import lombok.Builder;

@Builder
public record ArticleSaveServiceRequest(
        String title,
        String content
) {
}