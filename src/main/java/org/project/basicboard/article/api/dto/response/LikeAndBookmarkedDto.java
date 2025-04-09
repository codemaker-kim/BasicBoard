package org.project.basicboard.article.api.dto.response;

import lombok.Builder;

@Builder
public record LikeAndBookmarkedDto(
        boolean like,
        boolean bookmarked
) {
}
