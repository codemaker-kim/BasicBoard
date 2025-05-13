package org.project.basicboard.article.repository;

import com.querydsl.core.types.Order;
import org.project.basicboard.article.controller.dto.response.ArticlePageResponse;
import org.project.basicboard.article.domain.ArticleSortBy;
import org.project.basicboard.article.repository.dto.response.ArticleProjectionResponse;

import java.util.List;

public interface ArticleCustomRepository {
    List<ArticlePageResponse> getArticleSortedBy(Long articleId, Integer size, ArticleSortBy sortCriteria, Order order);

    List<ArticlePageResponse> getBookmarkedArticle(String username);

    ArticleProjectionResponse getArticle(Long articleId, String username);
}