package org.project.basicboard.comment.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.project.basicboard.comment.application.CommentMapper;
import org.project.basicboard.comment.application.CommentService;
import org.project.basicboard.comment.application.dto.request.AddCommentServiceRequest;
import org.project.basicboard.comment.application.dto.request.DeleteCommentServiceRequest;
import org.project.basicboard.comment.application.dto.request.UpdateCommentServiceRequest;
import org.project.basicboard.comment.controller.dto.request.AddCommentRequest;
import org.project.basicboard.comment.controller.dto.request.UpdateCommentRequest;
import org.project.basicboard.comment.controller.dto.response.CommentInfoResponse;
import org.project.basicboard.global.annotation.AuthUsername;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/{articleId}/comments")
public class CommentApiController {

    private final CommentService commentService;
    private final CommentMapper mapper;

    @GetMapping
    public ResponseEntity<List<CommentInfoResponse>> getAllArticleComments(@PathVariable("articleId") Long id) {
        List<CommentInfoResponse> response = commentService.findAllCommentInArticle(id);

        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<Long> addComment(@AuthUsername String username,
                                           @PathVariable("articleId") Long id,
                                           @RequestBody @Valid AddCommentRequest request) {

        Long commentId = commentService.addComment(AddCommentServiceRequest.of(id, request.content(), username));

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(commentId);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Void> updateComment(@AuthUsername String username,
                                              @PathVariable Long id,
                                              @RequestBody @Valid UpdateCommentRequest request) {
        commentService.updateComment(UpdateCommentServiceRequest.of(id, request.content(), username));

        return ResponseEntity.noContent()
                .build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteComment(@AuthUsername String username,
                                              @PathVariable Long id) {
        commentService.deleteComment(DeleteCommentServiceRequest.of(id, username));

        return ResponseEntity.noContent()
                .build();
    }
}
