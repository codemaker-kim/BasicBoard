package org.project.basicboard.article.controller;

import lombok.RequiredArgsConstructor;
import org.project.basicboard.article.controller.dto.request.ArticleSaveRequest;
import org.project.basicboard.article.controller.dto.request.UpdateArticleRequest;
import org.project.basicboard.article.application.ArticleService;
import org.project.basicboard.article.controller.dto.response.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import static org.project.basicboard.article.domain.ArticleSortBy.*;

@RestController
@RequestMapping("/api/articles")
@RequiredArgsConstructor
public class ArticleApiController {

    private final ArticleService articleService;

    //todo: Pageable 쓸거면 QueryParam으로 처리할 수 있는 방식이 있음.
    @PostMapping
    public ResponseEntity<ArticleSaveResponse> saveArticle(@RequestBody @Validated ArticleSaveRequest request) {
        Long articleId = articleService.createArticle(request);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ArticleSaveResponse(articleId));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<ArticleUpdateResponse> updateArticle(@PathVariable Long id, @RequestBody @Validated UpdateArticleRequest request) {
        ArticleUpdateResponse response = articleService.update(id, request);

        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ArticleDto> getArticle(@PathVariable Long id) {
        ArticleDto response = articleService.getArticle(id);

        return ResponseEntity.ok(response);
    }

    @GetMapping("/page/date")
    public ResponseEntity<Page<ArticlePageDto>> getArticlePageByTime(@RequestParam(defaultValue = "0") int page,
                                                                     @RequestParam(defaultValue = "10") int size) {
        Page<ArticlePageDto> response = articleService.getArticlePage(PageRequest.of(page, size), CREATE_AT);

        return ResponseEntity.ok(response);
    }

    @GetMapping("/page/number")
    public ResponseEntity<Page<ArticlePageDto>> getArticlePageById(@RequestParam(defaultValue = "0") int page,
                                                                   @RequestParam(defaultValue = "10") int size) {
        Page<ArticlePageDto> response = articleService.getArticlePage(PageRequest.of(page, size), ID);

        return ResponseEntity.ok(response);
    }

    @GetMapping("/page/title")
    public ResponseEntity<Page<ArticlePageDto>> getArticlePageByTitle(@RequestParam(defaultValue = "0") int page,
                                                                      @RequestParam(defaultValue = "10") int size) {
        Page<ArticlePageDto> response = articleService.getArticlePage(PageRequest.of(page, size), TITLE);

        return ResponseEntity.ok(response);
    }

    @GetMapping("/page/views")
    public ResponseEntity<Page<ArticlePageDto>> getArticlePageByViews(@RequestParam(defaultValue = "0") int page,
                                                                      @RequestParam(defaultValue = "10") int size) {
        Page<ArticlePageDto> response = articleService.getArticlePage(PageRequest.of(page, size), VIEWS);

        return ResponseEntity.ok(response);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<BookmarkedArticleDto> getBookmarkedArticle(@PathVariable("userId") Long id) {
        BookmarkedArticleDto response = articleService.getBookmarkedArticle(id);

        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteArticle(@PathVariable Long id) {
        articleService.deleteArticle(id);

        return ResponseEntity.noContent()
                .build();
    }
}