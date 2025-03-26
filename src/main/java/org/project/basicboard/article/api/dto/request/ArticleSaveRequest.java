package org.project.basicboard.article.api.dto.request;

import jakarta.validation.constraints.NotEmpty;

public record ArticleSaveRequest(
        @NotEmpty(message = "제목이 비어있습니다.") String title,
        @NotEmpty(message = "내용이 비어있습니다.") String content
) {
}
