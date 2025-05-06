package org.project.basicboard.comment.application.dto.request;

import lombok.Builder;

@Builder
public record UpdateCommentServiceRequest(
        Long id,
        String content,
        String username
) {
    public static UpdateCommentServiceRequest of(Long id, String content, String username) {
        return UpdateCommentServiceRequest.builder()
                .id(id)
                .content(content)
                .username(username)
                .build();
    }
}
