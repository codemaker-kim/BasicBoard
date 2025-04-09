package org.project.basicboard.article.domain.repository;

import org.project.basicboard.article.api.dto.response.ArticlePageDto;
import org.project.basicboard.article.api.dto.response.LikeAndBookmarkedDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ArticleCustomRepository {
    Page<ArticlePageDto> getArticleSortedBy(Pageable pageable, String sortCriteria);

    List<ArticlePageDto> getArticleBookmarked(String username);

    LikeAndBookmarkedDto isArticleLikeAndBookmarked(Long articleId, String username);
}
