package org.project.basicboard.comment.application;

import org.mapstruct.Mapper;
import org.project.basicboard.comment.controller.dto.response.CommentInfoResponse;
import org.project.basicboard.comment.domain.Comment;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CommentMapper {
    List<CommentInfoResponse> toCommentInfoResponse(List<Comment> comments);
}
