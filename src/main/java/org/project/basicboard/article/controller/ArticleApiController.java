package org.project.basicboard.article.controller;

import com.querydsl.core.types.Order;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.project.basicboard.article.application.ArticleMapper;
import org.project.basicboard.article.application.ArticleService;
import org.project.basicboard.article.controller.dto.request.ArticleSaveRequest;
import org.project.basicboard.article.controller.dto.request.ArticleUpdateRequest;
import org.project.basicboard.article.controller.dto.response.ArticleResponse;
import org.project.basicboard.article.controller.dto.response.ArticlePageDto;
import org.project.basicboard.article.domain.ArticleSortBy;
import org.project.basicboard.global.annotation.AuthUsername;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/articles")
public class ArticleApiController {

    private final ArticleService articleService;
    private final ArticleMapper mapper;

    @PostMapping
    public ResponseEntity<Long> saveArticle(@AuthUsername String username,
                                            @RequestBody @Valid ArticleSaveRequest request) {
        Long articleId = articleService.createArticle(
                username, mapper.toArticleSaveServiceRequest(request));

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(articleId);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Void> updateArticle(@AuthUsername String username,
                                              @PathVariable Long id,
                                              @RequestBody @Valid ArticleUpdateRequest request) {
        articleService.update(id, mapper.toArticleUpdateServiceRequest(request), username);

        return ResponseEntity.noContent()
                .build();
    }

    // 게시글
    @GetMapping("/{id}")
    public ResponseEntity<ArticleResponse> getArticle(@AuthUsername String username,
                                                      @PathVariable Long id) {
        ArticleResponse response = articleService.getArticle(id, username);

        return ResponseEntity.ok(response);
    }

    @GetMapping("/page")
    public ResponseEntity<List<ArticlePageDto>> getArticlePage(@RequestParam(required = false) Long articleId,
                                                               @RequestParam(defaultValue = "5", required = false) int size,
                                                               @RequestParam(defaultValue = "CREATE_AT") ArticleSortBy sortCriteria,
                                                               @RequestParam(defaultValue = "DESC") Order order) {
        List<ArticlePageDto> response = articleService.getArticlePage(articleId, size, sortCriteria, order);

        return ResponseEntity.ok(response);
    }

    @GetMapping("/bookmarked")
    public ResponseEntity<List<ArticlePageDto>> getBookmarkedArticle(@AuthUsername String username) {
        List<ArticlePageDto> response = articleService.getBookmarkedArticle(username);

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