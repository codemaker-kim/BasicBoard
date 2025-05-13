package org.project.basicboard.likes.repository;

import org.project.basicboard.likes.domain.Likes;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LikesRepository extends JpaRepository<Likes, Long> {
    Optional<Likes> findByArticleIdAndUsername(Long articleId, String username);

    boolean existsByArticleIdAndUsername(Long articleId, String username);
}