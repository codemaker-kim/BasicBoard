package org.project.basicboard.article.controller.dto.response;

import lombok.Builder;

@Builder
public record LikeAndBookmarkedDto(
        boolean like,
        boolean bookmarked
) {
}
