package org.project.basicboard.bookmark.application;

import lombok.RequiredArgsConstructor;
import org.project.basicboard.article.domain.Article;
import org.project.basicboard.article.exception.ArticleNotFoundException;
import org.project.basicboard.article.repository.ArticleRepository;
import org.project.basicboard.bookmark.domain.Bookmark;
import org.project.basicboard.bookmark.exception.BookmarkAlreadyExistException;
import org.project.basicboard.bookmark.exception.BookmarkNotExistException;
import org.project.basicboard.bookmark.repository.BookmarkRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BookmarkService {

    private final BookmarkRepository bookmarkRepository;
    private final ArticleRepository articleRepository;

    public void createBookmark(Long articleId, String username) {
        Bookmark bookmark = newBookmark(articleId, username);

        bookmarkRepository.save(bookmark);
    }

    public void deleteBookmark(Long articleId, String username) {
        Bookmark bookmark = bookmarkRepository.findByArticleIdAndUsername(articleId, username)
                .orElseThrow(BookmarkNotExistException::new);

        bookmarkRepository.delete(bookmark);
    }

    private Bookmark newBookmark(Long articleId, String username) {
        Article targetArticle = articleRepository.findById(articleId)
                .orElseThrow(ArticleNotFoundException::new);

        bookmarkExistCheck(articleId, username);

        return Bookmark.of(targetArticle, username);
    }

    private void bookmarkExistCheck(Long articleId, String username) {
        if (bookmarkRepository.existsByArticleIdAndUsername(articleId, username))
            throw new BookmarkAlreadyExistException();
    }
}