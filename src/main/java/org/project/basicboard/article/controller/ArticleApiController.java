package org.project.basicboard.article.controller;

import com.querydsl.core.types.Order;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.project.basicboard.article.application.ArticleService;
import org.project.basicboard.article.controller.dto.request.ArticleSaveRequest;
import org.project.basicboard.article.controller.dto.request.ArticleUpdateRequest;
import org.project.basicboard.article.controller.dto.response.ArticlePageResponse;
import org.project.basicboard.article.controller.dto.response.ArticleResponse;
import org.project.basicboard.article.domain.ArticleSortBy;
import org.project.basicboard.global.annotation.AuthUsername;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/articles")
public class ArticleApiController implements ArticleDocs {

    private final ArticleService articleService;

    @PostMapping
    public ResponseEntity<Long> saveArticle(
            @AuthUsername String username,
            @RequestBody @Valid ArticleSaveRequest request) {
        Long articleId = articleService.createArticle(username, request.toServiceRequest());

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(articleId);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Void> updateArticle(
            @AuthUsername String username,
            @PathVariable Long id,
            @RequestBody @Valid ArticleUpdateRequest request) {
        articleService.update(id, request.toServiceRequest(), username);

        return ResponseEntity.noContent()
                .build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ArticleResponse> getArticle(
            @AuthUsername String username,
            @PathVariable Long id) {
        ArticleResponse response = ArticleResponse.from(articleService.getArticle(id, username));

        return ResponseEntity.ok(response);
    }

    // TODO: 커스텀 페이지 반환 방식으로 바꿔주기
    @GetMapping
    public ResponseEntity<List<ArticlePageResponse>> getArticlePage(
            @RequestParam(required = false) Long articleId,
            @RequestParam(defaultValue = "5", required = false) int size,
            @RequestParam(defaultValue = "CREATE_AT") ArticleSortBy sortCriteria,
            //FIXME: DIP 위반 아닌가?
            @RequestParam(defaultValue = "DESC") Order order) {
        List<ArticlePageResponse> response = articleService.getArticlePage(articleId, size, sortCriteria, order);

        return ResponseEntity.ok(response);
    }

    // TODO: 커스텀 페이지 반환 방식으로 바꿔주기
    @GetMapping("/bookmarked")
    public ResponseEntity<List<ArticlePageResponse>> getBookmarkedArticle(
            @AuthUsername String username) {
        List<ArticlePageResponse> response = articleService.getBookmarkedArticle(username);

        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteArticle(
            @AuthUsername String username,
            @PathVariable Long id) {
        articleService.deleteArticle(id, username);

        return ResponseEntity.noContent()
                .build();
    }
}