package org.project.basicboard.likes.api;

import lombok.RequiredArgsConstructor;
import org.project.basicboard.global.annotation.AuthUsername;
import org.project.basicboard.likes.service.LikesService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/{articleId}/likes")
@RequiredArgsConstructor
public class LikesApiController {

    private final LikesService likesService;

    @PostMapping
    public ResponseEntity<Void> createOrDeleteLike(@AuthUsername String username,
                                                   @PathVariable("articleId") Long id) {
        likesService.createOrDeleteLike(id, username);

        return ResponseEntity.noContent()
                .build();
    }
}