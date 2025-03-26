package org.project.basicboard.comment.api;

import lombok.RequiredArgsConstructor;
import org.project.basicboard.comment.api.dto.request.AddCommentRequest;
import org.project.basicboard.comment.api.dto.request.UpdateCommentRequest;
import org.project.basicboard.comment.api.dto.response.AddCommentResponse;
import org.project.basicboard.comment.api.dto.response.UpdateCommentResponse;
import org.project.basicboard.comment.application.CommentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/comment")
@RequiredArgsConstructor
public class CommentApiController {

    private final CommentService commentService;

    /*@GetMapping("/{articleId}")
    public ResponseEntity<ArticleCommentResponse> getAllArticleComments(@PathVariable Long articleId) {

    }*/

    @PostMapping
    public ResponseEntity<AddCommentResponse> addComment(@RequestBody @Validated AddCommentRequest request) {
        AddCommentResponse response = commentService.addComment(request);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(response);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<UpdateCommentResponse> updateComment(@PathVariable Long id, @RequestBody @Validated UpdateCommentRequest request) {
        UpdateCommentResponse response = commentService.updateComment(id, request);

        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteComment(@PathVariable Long id) {
        commentService.deleteComment(id);

        return ResponseEntity.noContent()
                .build();
    }
}
