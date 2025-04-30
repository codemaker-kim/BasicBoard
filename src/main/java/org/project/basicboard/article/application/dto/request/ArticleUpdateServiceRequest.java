package org.project.basicboard.article.application.dto.request;

public record ArticleUpdateServiceRequest(
        String title,
        String content
) {
}
