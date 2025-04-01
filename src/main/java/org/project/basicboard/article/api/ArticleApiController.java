package org.project.basicboard.article.api;

import lombok.RequiredArgsConstructor;
import org.project.basicboard.article.api.dto.request.ArticleSaveRequest;
import org.project.basicboard.article.api.dto.request.UpdateArticleRequest;
import org.project.basicboard.article.api.dto.response.ArticleDto;
import org.project.basicboard.article.api.dto.response.ArticlePageDto;
import org.project.basicboard.article.api.dto.response.ArticleSaveResponse;
import org.project.basicboard.article.api.dto.response.ArticleUpdateResponse;
import org.project.basicboard.article.application.ArticleService;
import org.project.basicboard.article.domain.ArticleSortBy;
import org.project.basicboard.article.api.dto.response.BookmarkedArticleDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/articles")
@RequiredArgsConstructor
public class ArticleApiController {

    private final ArticleService articleService;

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
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, ArticleSortBy.CREATE_AT.getValue()));
        Page<ArticlePageDto> response = articleService.getArticlePage(pageable);

        return ResponseEntity.ok(response);
    }

    @GetMapping("/page/number")
    public ResponseEntity<Page<ArticlePageDto>> getArticlePageById(@RequestParam(defaultValue = "0") int page,
                                                                   @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, ArticleSortBy.ID.getValue()));
        Page<ArticlePageDto> response = articleService.getArticlePage(pageable);

        return ResponseEntity.ok(response);
    }

    @GetMapping("/page/title")
    public ResponseEntity<Page<ArticlePageDto>> getArticlePageByTitle(@RequestParam(defaultValue = "0") int page,
                                                                      @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, ArticleSortBy.TITLE.getValue()));
        Page<ArticlePageDto> response = articleService.getArticlePage(pageable);

        return ResponseEntity.ok(response);
    }

    @GetMapping("/page/views")
    public ResponseEntity<Page<ArticlePageDto>> getArticlePageByViews(@RequestParam(defaultValue = "0") int page,
                                                                      @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, ArticleSortBy.VIEWS.getValue()));
        Page<ArticlePageDto> response = articleService.getArticlePage(pageable);

        return ResponseEntity.ok(response);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<BookmarkedArticleDto> getBookmarkedArticle(@PathVariable("userId") Long id) {

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteArticle(@PathVariable Long id) {
        articleService.deleteArticle(id);

        return ResponseEntity.noContent()
                .build();
    }
}