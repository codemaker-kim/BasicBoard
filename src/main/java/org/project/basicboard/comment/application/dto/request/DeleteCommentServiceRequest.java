package org.project.basicboard.comment.application.dto.request;

import lombok.Builder;

@Builder
public record DeleteCommentServiceRequest(
        Long id,
        String username
) {
    public static DeleteCommentServiceRequest of(Long id, String username) {
        return DeleteCommentServiceRequest.builder()
                .id(id)
                .username(username)
                .build();
    }
}
