package org.project.basicboard.comment.api.dto.response;

import java.util.List;

public record ArticleCommentResponse(
        List<CommentInfoDto> commentInfo
) {
}
