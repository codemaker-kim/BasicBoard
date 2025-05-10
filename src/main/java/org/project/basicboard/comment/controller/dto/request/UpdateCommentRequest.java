package org.project.basicboard.comment.controller.dto.request;

import jakarta.validation.constraints.NotBlank;
import org.project.basicboard.comment.application.dto.request.UpdateCommentServiceRequest;

public record UpdateCommentRequest(
        @NotBlank(message = "업데이트할 댓글 내용이 비어있습니다.") String content
) {
    public UpdateCommentServiceRequest toServiceRequest(Long id, String username) {
        return UpdateCommentServiceRequest.builder()
                .id(id)
                .content(content)
                .username(username)
                .build();
    }
}