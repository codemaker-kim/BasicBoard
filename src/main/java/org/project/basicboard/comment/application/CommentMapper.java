package org.project.basicboard.comment.application;

import org.project.basicboard.comment.api.dto.response.ArticleCommentResponse;
import org.project.basicboard.comment.api.dto.response.CommentInfoDto;
import org.project.basicboard.comment.api.dto.response.CommentResponse;
import org.project.basicboard.comment.domain.Comment;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CommentMapper {
    public CommentResponse toCommentResponse(Comment comment) {
        return CommentResponse.from(comment);
    }

    public ArticleCommentResponse toArticleCommentResponse(List<Comment> comments) {
        List<CommentInfoDto> infoDtoList = makeInfoDtoList(comments);

        return new ArticleCommentResponse(infoDtoList);
    }

    private List<CommentInfoDto> makeInfoDtoList(List<Comment> comments) {
        return comments.stream()
                .map(CommentInfoDto::from)
                .toList();
    }
}