package org.project.basicboard.article.api.dto.request;

import jakarta.validation.constraints.NotBlank;

public record UpdateArticleRequest(
        @NotBlank(message = "수정할 글 제목이 비어있습니다.") String title,
        @NotBlank(message = "수정할 글 내용이 비어있습니다.") String content
) {
}