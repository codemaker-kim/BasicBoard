package org.project.basicboard.article.api;

import lombok.RequiredArgsConstructor;
import org.project.basicboard.article.api.dto.ArticleDto;
import org.project.basicboard.article.api.dto.request.ArticleSaveRequest;
import org.project.basicboard.article.api.dto.request.UpdateArticleRequest;
import org.project.basicboard.article.api.dto.response.ArticleSaveResponse;
import org.project.basicboard.article.application.ArticleService;
import org.project.basicboard.article.domain.Article;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("/api/article")
@RequiredArgsConstructor
public class ArticleApiController {

    private final ArticleService articleService;

    @PostMapping
    public ResponseEntity<ArticleSaveResponse> saveArticle(@RequestBody @Validated ArticleSaveRequest request) {
        Long articleId = articleService.createArticle(request);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ArticleSaveResponse(articleId));
    }

    /*@PatchMapping("/{id}")
    public ResponseEntity<ArticleUpdateResponse> updateArticle(@RequestBody @Validated UpdateArticleRequest request) {
        articleService.updateArticle(request);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ArticleDto> getArticle(@PathVariable Long id) {

    }*/

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteArticle(@PathVariable Long id) {
        articleService.deleteArticle(id);

        return ResponseEntity.noContent()
                .build();
    }
}