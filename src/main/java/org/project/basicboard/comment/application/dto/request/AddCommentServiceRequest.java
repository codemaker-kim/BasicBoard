package org.project.basicboard.comment.application.dto.request;

import lombok.Builder;

@Builder
public record AddCommentServiceRequest(
        Long id,
        String content,
        String username
) {
    public static AddCommentServiceRequest of(Long id, String content, String username) {
        return AddCommentServiceRequest.builder()
                .id(id)
                .content(content)
                .username(username)
                .build();
    }
}
