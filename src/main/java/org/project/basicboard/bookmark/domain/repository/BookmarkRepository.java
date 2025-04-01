package org.project.basicboard.bookmark.domain.repository;

import org.project.basicboard.bookmark.domain.Bookmark;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BookmarkRepository extends JpaRepository<Bookmark, Long> {
    Optional<Bookmark> findByUsernameAndArticleId(String username, Long articleId);
}
