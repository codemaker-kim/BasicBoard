package org.project.basicboard.bookmark.api.dto;

import org.project.basicboard.article.api.dto.ArticlePageDto;

import java.util.List;

public record BookmarkedArticleDto(
        List<ArticlePageDto> markedArticles
) {
}