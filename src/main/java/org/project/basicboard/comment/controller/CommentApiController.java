package org.project.basicboard.comment.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.project.basicboard.comment.application.CommentService;
import org.project.basicboard.comment.application.dto.response.CommentInfoServiceResponse;
import org.project.basicboard.comment.controller.dto.request.AddCommentRequest;
import org.project.basicboard.comment.controller.dto.request.UpdateCommentRequest;
import org.project.basicboard.comment.controller.dto.response.CommentInfoResponse;
import org.project.basicboard.global.annotation.AuthUsername;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/{articleId}/comments")
public class CommentApiController implements CommentDocs{

    private final CommentService commentService;

    @GetMapping
    public ResponseEntity<CommentInfoResponse> getAllArticleComments(
            @PathVariable Long articleId) {
        CommentInfoServiceResponse result = commentService.findAllCommentInArticle(articleId);

        CommentInfoResponse response = CommentInfoResponse.from(result);

        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<Long> addComment(
            @AuthUsername String username,
            @PathVariable Long articleId,
            @RequestBody @Valid AddCommentRequest request) {
        Long commentId = commentService.addComment(request.toServiceRequest(articleId, username));

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(commentId);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Void> updateComment(
            @AuthUsername String username,
            @PathVariable Long id,
            @RequestBody @Valid UpdateCommentRequest request) {
        commentService.updateComment(request.toServiceRequest(id, username));

        return ResponseEntity.noContent()
                .build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteComment(
            @AuthUsername String username,
            @PathVariable Long id) {
        commentService.deleteComment(id, username);

        return ResponseEntity.noContent()
                .build();
    }
}