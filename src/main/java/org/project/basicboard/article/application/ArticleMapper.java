package org.project.basicboard.article.application;

import org.mapstruct.Mapper;
import org.project.basicboard.article.controller.dto.response.ArticleSimpleResponse;
import org.project.basicboard.article.domain.Article;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ArticleMapper {
    List<ArticleSimpleResponse> toPageResponse(List<Article> articles);
}
