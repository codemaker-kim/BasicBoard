package org.project.basicboard.comment.controller.dto.request;

import jakarta.validation.constraints.NotBlank;
import org.project.basicboard.comment.application.dto.request.AddCommentServiceRequest;

public record AddCommentRequest(
        @NotBlank(message = "댓글 내용이 비어있습니다.") String content
) {
    public AddCommentServiceRequest toServiceRequest(Long articleId, String username) {
        return AddCommentServiceRequest.builder()
                .articleId(articleId)
                .content(content)
                .username(username)
                .build();
    }
}
