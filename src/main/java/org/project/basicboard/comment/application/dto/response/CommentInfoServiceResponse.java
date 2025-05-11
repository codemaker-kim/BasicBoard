package org.project.basicboard.comment.application.dto.response;

import lombok.Builder;

import java.util.List;

@Builder
public record CommentInfoServiceResponse(
        List<CommentDetailServiceResponse> commentInfos
) {
    public static CommentInfoServiceResponse of(List<CommentDetailServiceResponse> commentDetails) {
        return CommentInfoServiceResponse.builder()
                .commentInfos(commentDetails)
                .build();
    }
}