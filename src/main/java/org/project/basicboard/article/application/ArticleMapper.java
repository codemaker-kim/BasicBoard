package org.project.basicboard.article.application;

import org.mapstruct.Mapper;
import org.project.basicboard.article.application.dto.request.ArticleSaveServiceRequest;
import org.project.basicboard.article.application.dto.request.ArticleUpdateServiceRequest;
import org.project.basicboard.article.controller.dto.request.ArticleSaveRequest;
import org.project.basicboard.article.controller.dto.request.ArticleUpdateRequest;

@Mapper(componentModel = "spring")
public interface ArticleMapper {
    ArticleSaveServiceRequest toArticleSaveServiceRequest(ArticleSaveRequest articleSaveRequest);

    ArticleUpdateServiceRequest toArticleUpdateServiceRequest(ArticleUpdateRequest articleUpdateRequest);
}
