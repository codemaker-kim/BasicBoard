package org.project.basicboard.article.controller.dto.request;

import jakarta.validation.constraints.NotBlank;
import org.project.basicboard.article.application.dto.request.ArticleUpdateServiceRequest;

public record ArticleUpdateRequest(
        @NotBlank(message = "수정할 글 제목이 비어있습니다.") String title,
        @NotBlank(message = "수정할 글 내용이 비어있습니다.") String content
) {
    public ArticleUpdateServiceRequest toServiceRequest() {
        return ArticleUpdateServiceRequest.builder()
                .title(title)
                .content(content)
                .build();
    }
}