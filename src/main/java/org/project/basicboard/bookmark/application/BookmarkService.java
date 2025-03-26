package org.project.basicboard.bookmark.application;

import lombok.RequiredArgsConstructor;
import org.project.basicboard.article.domain.Article;
import org.project.basicboard.article.domain.repository.ArticleRepository;
import org.project.basicboard.article.exception.ArticleNotFoundException;
import org.project.basicboard.bookmark.domain.Bookmark;
import org.project.basicboard.bookmark.domain.repository.BookmarkRepository;
import org.project.basicboard.bookmark.exception.AlreadyDeletedBookmarkException;
import org.project.basicboard.bookmark.exception.BookmarkNotFoundException;
import org.project.basicboard.bookmark.exception.NotAuthorizeBookmarkException;
import org.project.basicboard.global.security.SecurityUtil;
import org.project.basicboard.bookmark.api.dto.response.BookmarkResponse;
import org.project.basicboard.user.domain.User;
import org.project.basicboard.user.domain.repository.UserRepository;
import org.project.basicboard.user.exception.UserNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class BookmarkService {

    private final BookmarkRepository bookmarkRepository;
    private final UserRepository userRepository;
    private final ArticleRepository articleRepository;
    private final SecurityUtil securityUtil;

    @Transactional
    public BookmarkResponse saveBookmark(Long articleId) {
        Bookmark bookmark = createBookmark(articleId);

        bookmarkRepository.save(bookmark);

        return new BookmarkResponse(bookmark.getId());
    }

    @Transactional
    public void changeStatus(Long bookmarkId) {
        Bookmark bookmark = bookmarkRepository.findById(bookmarkId)
                .orElseThrow(BookmarkNotFoundException::new);

        authorizeBookmark(bookmark);

        bookmarkStatusUpdate(bookmark);
    }

    private void bookmarkStatusUpdate(Bookmark bookmark) {
        if (!(bookmark.getStatus() == Status.ACTIVE))
            throw new AlreadyDeletedBookmarkException();

        bookmark.changeStatus();
    }

    private Bookmark createBookmark(Long articleId) {
        User user = userRepository.findByUsername(securityUtil.getCurrentUser())
                .orElseThrow(UserNotFoundException::new);

        Article article = articleRepository.findById(articleId)
                .orElseThrow(ArticleNotFoundException::new);

        return getBookmark(user, article);
    }

    private Bookmark getBookmark(User user, Article article) {
        if(bookmarkRepository.existsByUserIdAndArticleId(user.getId(), article.getId())) {
            Bookmark bookmark = bookmarkRepository.findByUserIdAndArticleId(user.getId(), article.getId())
                    .orElseThrow(BookmarkNotFoundException::new);

            bookmark.changeStatus();
            return bookmark;
        }

        return Bookmark.builder()
                .user(user)
                .article(article)
                .build();
    }

    private void authorizeBookmark(Bookmark bookmark) {
        String currentUsername = securityUtil.getCurrentUser();

        if (!bookmark.getUser().getUsername().equals(currentUsername))
            throw new NotAuthorizeBookmarkException();
    }
}
