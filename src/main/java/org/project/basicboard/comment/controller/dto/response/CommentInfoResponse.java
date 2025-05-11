package org.project.basicboard.comment.controller.dto.response;

import lombok.Builder;
import org.project.basicboard.comment.application.dto.response.CommentInfoServiceResponse;

import java.util.List;

@Builder
public record CommentInfoResponse(
        List<CommentDetailResponse> commentInfos
) {
    public static CommentInfoResponse from(CommentInfoServiceResponse response) {
        return CommentInfoResponse.builder()
                .commentInfos(
                        toDetailResponse(response))
                .build();
    }

    private static List<CommentDetailResponse> toDetailResponse(CommentInfoServiceResponse response) {
        return response.commentInfos().stream()
                .map(CommentDetailResponse::from)
                .toList();
    }
}