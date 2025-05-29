package org.project.basicboard.article.repository;

import org.project.basicboard.article.domain.Article;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ArticleRepository extends JpaRepository<Article, Long>, ArticleCustomRepository {
    @Modifying(clearAutomatically = true)
    @Query("update Article a SET a.views = a.views + 1 where a.id = :id")
    void increaseViewCount(Long id);

    @Query("select a from Article a join Bookmark b where b.username = :username")
    List<Article> getBookmarkedArticles(String username);
}