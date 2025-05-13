package org.project.basicboard.bookmark.api;

import lombok.RequiredArgsConstructor;
import org.project.basicboard.bookmark.application.BookmarkService;
import org.project.basicboard.global.annotation.AuthUsername;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/{articleId}/bookmarks")
public class BookmarkApiController implements BookmarkDocs {

    private final BookmarkService bookmarkService;

    @PostMapping
    public ResponseEntity<Void> createBookmark(
            @AuthUsername String username,
            @PathVariable Long articleId) {
        bookmarkService.createBookmark(articleId, username);

        return ResponseEntity.noContent()
                .build();
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteBookmark(
            @AuthUsername String username,
            @PathVariable Long articleId
    ) {
        bookmarkService.deleteBookmark(articleId, username);

        return ResponseEntity.noContent()
                .build();
    }
}