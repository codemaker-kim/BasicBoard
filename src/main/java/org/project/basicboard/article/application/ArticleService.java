package org.project.basicboard.article.application;

import lombok.RequiredArgsConstructor;
import org.project.basicboard.article.api.dto.request.ArticleSaveRequest;
import org.project.basicboard.article.api.dto.request.UpdateArticleRequest;
import org.project.basicboard.article.api.dto.response.ArticleDto;
import org.project.basicboard.article.api.dto.response.ArticlePageDto;
import org.project.basicboard.article.api.dto.response.ArticleUpdateResponse;
import org.project.basicboard.article.api.dto.response.BookmarkedArticleDto;
import org.project.basicboard.article.domain.Article;
import org.project.basicboard.article.domain.ArticleSortBy;
import org.project.basicboard.article.domain.repository.ArticleRepository;
import org.project.basicboard.article.exception.ArticleNotFoundException;
import org.project.basicboard.comment.domain.repository.CommentRepository;
import org.project.basicboard.global.security.SecurityUtil;
import org.project.basicboard.user.domain.User;
import org.project.basicboard.user.domain.repository.UserRepository;
import org.project.basicboard.user.exception.UserNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class ArticleService {

    private final ArticleRepository articleRepository;
    private final CommentRepository commentRepository;
    private final UserRepository userRepository;

    public Long createArticle(ArticleSaveRequest dto) {
        String authorName = SecurityUtil.getCurrentUser();
        Article article = Article.builder()
                .title(dto.title())
                .content(dto.content())
                .author(authorName)
                .build();

        articleRepository.save(article);

        return article.getId();
    }

    public void deleteArticle(Long articleId) {
        Article article = articleRepository.findById(articleId)
                .orElseThrow(ArticleNotFoundException::new);

        String currentUser = SecurityUtil.getCurrentUser();

        article.validateAuthor(currentUser);

        commentRepository.deleteAllByArticleId(articleId);
        articleRepository.delete(article);
    }

    public ArticleUpdateResponse update(Long id, UpdateArticleRequest dto) {
        Article article = articleRepository.findById(id)
                .orElseThrow(ArticleNotFoundException::new);

        String currentUsername = SecurityUtil.getCurrentUser();
        article.validateAuthor(currentUsername);

        article.update(dto.title(), dto.content());

        return ArticleUpdateResponse.builder()
                .articleId(article.getId())
                .title(article.getTitle())
                .content(article.getContent())
                .build();
    }

    public ArticleDto getArticle(Long id) {
        Article article = articleRepository.findById(id)
                .orElseThrow(ArticleNotFoundException::new);

        article.increaseViews();

        return ArticleDto.from(article);
    }

    public Page<ArticlePageDto> getArticlePage(Pageable pageable, ArticleSortBy sortCriteria) {
        return articleRepository.getArticleSortedBy(pageable, sortCriteria.getValue());
    }

    public BookmarkedArticleDto getBookmarkedArticle(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(UserNotFoundException::new);

        List<ArticlePageDto> bookmarkedList = articleRepository.getArticleBookmarked(user.getUsername());

        return new BookmarkedArticleDto(bookmarkedList);
    }
}
