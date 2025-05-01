package org.project.basicboard.comment.domain.repository;

import org.project.basicboard.comment.api.dto.response.CommentInfoDto;
import org.project.basicboard.comment.domain.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    void deleteAllByArticleId(Long articleId);

    List<Comment> findAllByArticleId(Long articleId);

    @Query("SELECT new org.project.basicboard.comment.api.dto.response.CommentInfoDto(c.id, c.writer, c.content) " +
            "FROM Comment c WHERE c.article.id = :articleId")
    List<CommentInfoDto> findCommentInfoByArticleId(Long articleId);
}