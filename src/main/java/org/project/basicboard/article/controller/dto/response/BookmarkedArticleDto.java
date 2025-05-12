package org.project.basicboard.article.controller.dto.response;

import java.util.List;

public record BookmarkedArticleDto(
        List<ArticlePageResponse> markedArticles
) {
}