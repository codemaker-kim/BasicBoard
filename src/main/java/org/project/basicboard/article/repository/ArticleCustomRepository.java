package org.project.basicboard.article.repository;

import com.querydsl.core.types.Order;
import org.project.basicboard.article.controller.dto.response.ArticleDto;
import org.project.basicboard.article.controller.dto.response.ArticlePageDto;
import org.project.basicboard.article.controller.dto.response.LikeAndBookmarkedDto;
import org.project.basicboard.article.domain.ArticleSortBy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ArticleCustomRepository {
    List<ArticlePageDto> getArticleSortedBy(Long articleId, Integer size, ArticleSortBy sortCriteria, Order order);

    List<ArticlePageDto> getBookmarkedArticle(String username);
}