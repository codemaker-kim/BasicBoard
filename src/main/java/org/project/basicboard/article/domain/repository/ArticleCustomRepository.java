package org.project.basicboard.article.domain.repository;

import org.project.basicboard.article.api.dto.response.ArticlePageDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ArticleCustomRepository {
    Page<ArticlePageDto> getArticleSortedBy(Pageable pageable, String sortCriteria);
}
