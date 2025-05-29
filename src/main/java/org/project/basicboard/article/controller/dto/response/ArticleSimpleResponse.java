package org.project.basicboard.article.controller.dto.response;

import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record ArticleSimpleResponse(
        Long id,
        String title,
        LocalDateTime createdAt,
        Integer views
) {
}