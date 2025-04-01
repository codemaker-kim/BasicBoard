package org.project.basicboard.article.api.dto.response;

import java.util.List;

public record BookmarkedArticleDto(
        List<ArticlePageDto> markedArticles
) {
}