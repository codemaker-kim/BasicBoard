package org.project.basicboard.article.application.dto.request;

public record ArticleSaveServiceRequest(
        String title,
        String content
) {
}
