package org.project.basicboard.comment.application;

import org.mapstruct.Mapper;
import org.project.basicboard.comment.application.dto.CommentInfoServiceResponse;
import org.project.basicboard.comment.domain.Comment;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CommentMapper {
    List<CommentInfoServiceResponse> toServiceResponse(List<Comment> comments);
}
