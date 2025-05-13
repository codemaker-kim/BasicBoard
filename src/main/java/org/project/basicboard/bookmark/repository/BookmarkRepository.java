package org.project.basicboard.bookmark.repository;

import org.project.basicboard.bookmark.domain.Bookmark;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface BookmarkRepository extends JpaRepository<Bookmark, Long> {
    Optional<Bookmark> findByArticleIdAndUsername(Long articleId, String username);

    boolean existsByArticleIdAndUsername(Long articleId, String username);
}