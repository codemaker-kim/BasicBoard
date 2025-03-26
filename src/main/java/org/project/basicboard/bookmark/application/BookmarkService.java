package org.project.basicboard.bookmark.application;

import lombok.RequiredArgsConstructor;
import org.project.basicboard.article.domain.Article;
import org.project.basicboard.article.domain.repository.ArticleRepository;
import org.project.basicboard.article.exception.ArticleNotFoundException;
import org.project.basicboard.bookmark.api.BookmarkApiController;
import org.project.basicboard.bookmark.domain.Bookmark;
import org.project.basicboard.bookmark.domain.repository.BookmarkRepository;
import org.project.basicboard.global.security.SecurityUtil;
import org.project.basicboard.likes.domain.Likes;
import org.project.basicboard.user.domain.User;
import org.project.basicboard.user.domain.repository.UserRepository;
import org.project.basicboard.user.exception.UserNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class BookmarkService {

    private final BookmarkRepository bookmarkRepository;
    private final UserRepository userRepository;
    private final ArticleRepository articleRepository;
    private final SecurityUtil securityUtil;
    private final BookmarkApiController bookmarkApiController;

    public void createOrDeleteBookmark(Long articleId) {
        User user = userRepository.findByUsername(securityUtil.getCurrentUser())
                .orElseThrow(UserNotFoundException::new);

        Article article = articleRepository.findById(articleId)
                .orElseThrow(ArticleNotFoundException::new);

        createOrDeleteProcess(user, article);
    }

    private void createOrDeleteProcess(User user, Article article) {
        if (bookmarkRepository.existsByUserIdAndArticleId(user.getId(), article.getId())) {
            Bookmark bookmark = bookmarkRepository.findByUserIdAndArticleId(user.getId(), article.getId()).get();
            bookmarkRepository.delete(bookmark);
        } else {
            Bookmark bookmark = Bookmark.builder()
                    .user(user)
                    .article(article)
                    .build();

            bookmarkRepository.save(bookmark);
        }
    }
}