package org.project.basicboard.article.controller;

import com.querydsl.core.types.Order;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.project.basicboard.article.application.ArticleService;
import org.project.basicboard.article.controller.dto.request.ArticleSaveRequest;
import org.project.basicboard.article.controller.dto.request.UpdateArticleRequest;
import org.project.basicboard.article.controller.dto.response.ArticleDto;
import org.project.basicboard.article.controller.dto.response.ArticlePageDto;
import org.project.basicboard.article.controller.dto.response.ArticleUpdateResponse;
import org.project.basicboard.article.controller.dto.response.BookmarkedArticleDto;
import org.project.basicboard.article.domain.ArticleSortBy;
import org.project.basicboard.global.annotation.AuthUsername;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/articles")
public class ArticleApiController {

    private final ArticleService articleService;

    //todo: Pageable 쓸거면 QueryParam으로 처리할 수 있는 방식이 있음.
    @PostMapping
    public ResponseEntity<Long> saveArticle(@AuthUsername String username,
                                            @RequestBody @Valid ArticleSaveRequest request) {
        Long articleId = articleService.createArticle(username, request);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(articleId);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<ArticleUpdateResponse> updateArticle(@AuthUsername String username,
                                                               @PathVariable Long id,
                                                               @RequestBody @Valid UpdateArticleRequest request) {
        ArticleUpdateResponse response = articleService.update(id, request, username);

        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ArticleDto> getArticle(@PathVariable Long id) {
        ArticleDto response = articleService.getArticle(id);

        return ResponseEntity.ok(response);
    }

    @GetMapping("/page")
    public ResponseEntity<List<ArticlePageDto>> getArticlePage(@RequestParam(required = false) Long articleId,
                                                               @RequestParam(defaultValue = "5", required = false) Integer size,
                                                               @RequestParam("sortCriteria") ArticleSortBy sortCriteria,
                                                               @RequestParam("order") Order order) {
        List<ArticlePageDto> response = articleService.getArticlePage(articleId, size, sortCriteria, order);

        return ResponseEntity.ok(response);
    }

    @GetMapping("/bookmarked")
    public ResponseEntity<BookmarkedArticleDto> getBookmarkedArticle(@AuthUsername String username) {
        BookmarkedArticleDto response = articleService.getBookmarkedArticle(username);

        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteArticle(@AuthUsername String username,
                                              @PathVariable Long id) {
        articleService.deleteArticle(id, username);

        return ResponseEntity.noContent()
                .build();
    }
}