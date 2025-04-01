package org.project.basicboard.comment.api.dto.response;

import lombok.Builder;
import org.project.basicboard.comment.domain.Comment;

@Builder
public record CommentInfoDto(
        Long id,
        String username,
        String content
) {
    public static CommentInfoDto from(Comment comment) {
        return CommentInfoDto.builder()
                .id(comment.getId())
                .username(comment.getWriter())
                .content(comment.getContent())
                .build();
    }
}
