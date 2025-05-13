package org.project.basicboard.article.controller.dto.request;

import jakarta.validation.constraints.NotBlank;
import org.project.basicboard.article.application.dto.request.ArticleSaveServiceRequest;

public record ArticleSaveRequest(
        @NotBlank(message = "제목이 비어있습니다.") String title,
        @NotBlank(message = "내용이 비어있습니다.") String content
) {
    public ArticleSaveServiceRequest toServiceRequest() {
        return ArticleSaveServiceRequest.builder()
                .title(title)
                .content(content)
                .build();
    }
}