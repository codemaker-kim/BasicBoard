package org.project.basicboard.article.api.dto.request;

import jakarta.validation.constraints.NotBlank;

public record ArticleSaveRequest(
        @NotBlank(message = "제목이 비어있습니다.") String title,
        @NotBlank(message = "내용이 비어있습니다.") String content
) {
}
