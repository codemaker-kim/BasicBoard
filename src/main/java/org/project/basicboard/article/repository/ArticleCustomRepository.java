package org.project.basicboard.article.repository;

import com.querydsl.core.types.Order;
import org.project.basicboard.article.controller.dto.response.ArticlePageDto;
import org.project.basicboard.article.domain.ArticleSortBy;

import java.util.List;

public interface ArticleCustomRepository {
    List<ArticlePageDto> getArticleSortedBy(Long articleId, Integer size, ArticleSortBy sortCriteria, Order order);

    List<ArticlePageDto> getBookmarkedArticle(String username);
}