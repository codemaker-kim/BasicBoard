package org.project.basicboard.bookmark.api;

import lombok.RequiredArgsConstructor;
import org.project.basicboard.bookmark.application.BookmarkService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/bookmarks")
@RequiredArgsConstructor
public class BookmarkApiController {

    private final BookmarkService bookmarkService;

    @PostMapping("/{articleId}")
    public ResponseEntity<Void> createOrDeleteBookmark(@PathVariable("articleId") Long id) {
        bookmarkService.createOrDeleteBookmark(id);

        return ResponseEntity.noContent()
                .build();
    }
}