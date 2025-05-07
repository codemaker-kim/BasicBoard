package org.project.basicboard.likes.controller;

import lombok.RequiredArgsConstructor;
import org.project.basicboard.global.annotation.AuthUsername;
import org.project.basicboard.likes.service.LikesService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/articles/{articleId}/likes")
public class LikesApiController implements LikesDocs{

    private final LikesService likesService;

    @PostMapping
    public ResponseEntity<Void> createLike(@AuthUsername String username,
                                           @PathVariable("articleId") Long id) {
        likesService.createLike(id, username);

        return ResponseEntity.noContent()
                .build();
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteLike(@AuthUsername String username,
                                           @PathVariable("articleId") Long id) {
        likesService.deleteLike(id, username);

        return ResponseEntity.noContent()
                .build();
    }
}