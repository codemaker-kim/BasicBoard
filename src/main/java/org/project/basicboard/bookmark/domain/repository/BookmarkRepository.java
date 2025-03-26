package org.project.basicboard.bookmark.domain.repository;

import org.project.basicboard.bookmark.domain.Bookmark;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BookmarkRepository extends JpaRepository<Bookmark, Long> {
    boolean existsByUserIdAndArticleId(Long userId, Long articleId);

    Optional<Bookmark> findByUserIdAndArticleId(Long userId, Long articleId);

    List<Bookmark> findByUserId(Long userId);
}
