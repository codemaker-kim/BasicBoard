package org.project.basicboard.bookmark.api;

import lombok.RequiredArgsConstructor;
import org.project.basicboard.bookmark.api.dto.response.BookmarkResponse;
import org.project.basicboard.bookmark.application.BookmarkService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/bookmarks")
@RequiredArgsConstructor
public class BookmarkApiController {

    private final BookmarkService bookmarkService;

    @PostMapping("/{articleId}")
    public ResponseEntity<BookmarkResponse> createBookmark(@PathVariable("articleId") Long id) {
        BookmarkResponse response = bookmarkService.saveBookmark(id);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(response);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Void> deleteBookmark(@PathVariable Long id) {
        bookmarkService.changeStatus(id);

        return ResponseEntity.noContent()
                .build();
    }
}