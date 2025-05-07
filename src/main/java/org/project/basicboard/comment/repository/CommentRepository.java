package org.project.basicboard.comment.repository;

import org.project.basicboard.comment.controller.dto.response.CommentInfoResponse;
import org.project.basicboard.comment.domain.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    void deleteAllByArticleId(Long articleId);

    List<Comment> findAllByArticleId(Long articleId);

    @Query("SELECT new org.project.basicboard.comment.controller.dto.response.CommentInfoResponse(c.writer, c.content) " +
            "FROM Comment c WHERE c.article.id = :articleId")
    List<CommentInfoResponse> findCommentInfoByArticleId(Long articleId);
}