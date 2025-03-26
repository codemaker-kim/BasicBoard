package org.project.basicboard.comment.domain.repository;

import org.project.basicboard.comment.domain.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    void deleteAllByArticleId(Long articleId);

    List<Comment> findAllByArticleId(Long articleId);
}