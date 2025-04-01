package org.project.basicboard.bookmark.application;

import lombok.RequiredArgsConstructor;
import org.project.basicboard.article.domain.Article;
import org.project.basicboard.article.domain.repository.ArticleRepository;
import org.project.basicboard.article.exception.ArticleNotFoundException;
import org.project.basicboard.bookmark.domain.Bookmark;
import org.project.basicboard.bookmark.domain.repository.BookmarkRepository;
import org.project.basicboard.global.security.SecurityUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class BookmarkService {

    private final BookmarkRepository bookmarkRepository;
    private final ArticleRepository articleRepository;

    public void createOrDeleteBookmark(Long articleId) {
        Article article = articleRepository.findById(articleId)
                .orElseThrow(ArticleNotFoundException::new);

        createOrDeleteProcess(article);
    }

    private void createOrDeleteProcess(Article article) {
        String currentUser = SecurityUtil.getCurrentUser();

        Optional<Bookmark> bookmark = bookmarkRepository.findByUsernameAndArticleId(currentUser, article.getId());

        if (bookmark.isPresent()) {
            bookmarkRepository.delete(bookmark.get());
            return;
        }

        Bookmark newBookmark = Bookmark.builder()
                .article(article)
                .username(currentUser)
                .build();

        bookmarkRepository.save(newBookmark);
    }
}