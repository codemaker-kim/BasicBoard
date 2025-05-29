package org.project.basicboard.article.repository;

import org.project.basicboard.article.controller.dto.response.ArticleSimpleResponse;
import org.project.basicboard.article.repository.dto.response.ArticleProjectionResponse;

import java.util.List;

public interface ArticleCustomRepository {
    List<ArticleSimpleResponse> getArticleSortedBy(int pageNumber, int size, final String sortCriteria, final String sortDirection);

    ArticleProjectionResponse getArticle(Long articleId, String username);
}