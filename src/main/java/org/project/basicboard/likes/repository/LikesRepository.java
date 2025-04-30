package org.project.basicboard.likes.repository;

import org.project.basicboard.likes.domain.Likes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface LikesRepository extends JpaRepository<Likes, Long> {
    Optional<Likes> findByUsernameAndArticleId(String username, Long articleId);

    @Modifying
    @Query("delete from Likes l where l.article.id =:articleId and l.username =:username")
    void deleteByArticleIdAndUsername(@Param("articleId") Long articleId, @Param("username") String username);
}
