package org.project.basicboard.likes.repository;

import jakarta.persistence.LockModeType;
import org.project.basicboard.likes.domain.Likes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface LikesRepository extends JpaRepository<Likes, Long> {

    @Modifying
    @Lock(LockModeType.OPTIMISTIC)
    @Query("delete from Likes l where l.article.id =:articleId and l.username =:username")
    void deleteByArticleIdAndUsername(@Param("articleId") Long articleId, @Param("username") String username);

    boolean existsByUsernameAndArticleId(String username, Long articleId);
}
