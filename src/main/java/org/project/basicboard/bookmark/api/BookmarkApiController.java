package org.project.basicboard.bookmark.api;

import lombok.RequiredArgsConstructor;
import org.project.basicboard.bookmark.application.BookmarkService;
import org.project.basicboard.global.annotation.AuthUsername;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/{articleId}/bookmarks")
public class BookmarkApiController implements BookmarkDocs{

    private final BookmarkService bookmarkService;

    @PostMapping
    public ResponseEntity<Void> createOrDeleteBookmark(@AuthUsername String username,
                                                       @PathVariable("articleId") Long id) {
        bookmarkService.createOrDeleteBookmark(id, username);

        return ResponseEntity.noContent()
                .build();
    }
}