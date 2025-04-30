package org.project.basicboard.article.application;

import com.querydsl.core.types.Order;
import lombok.RequiredArgsConstructor;
import org.project.basicboard.article.controller.dto.request.ArticleSaveRequest;
import org.project.basicboard.article.controller.dto.request.UpdateArticleRequest;
import org.project.basicboard.article.controller.dto.response.*;
import org.project.basicboard.article.domain.Article;
import org.project.basicboard.article.domain.ArticleSortBy;
import org.project.basicboard.article.repository.ArticleRepository;
import org.project.basicboard.article.exception.ArticleNotFoundException;
import org.project.basicboard.comment.domain.Comment;
import org.project.basicboard.comment.domain.repository.CommentRepository;
import org.project.basicboard.global.security.SecurityUtil;
import org.project.basicboard.user.domain.User;
import org.project.basicboard.user.repository.UserRepository;
import org.project.basicboard.user.exception.UserNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ArticleService {

    private final ArticleRepository articleRepository;
    private final CommentRepository commentRepository;
    private final UserRepository userRepository;
    private final ArticleMapper mapper;

    public Long createArticle(String username, ArticleSaveRequest dto) {

        Article article = Article.builder()
                .title(dto.title())
                .content(dto.content())
                .author(username)
                .build();

        articleRepository.save(article);

        return article.getId();
    }

    public void deleteArticle(Long articleId, String username) {
        Article article = articleRepository.findById(articleId)
                .orElseThrow(ArticleNotFoundException::new);

        article.validateAuthor(username);

        commentRepository.deleteAllByArticleId(articleId);
        articleRepository.delete(article);
    }

    @Transactional
    public ArticleUpdateResponse update(Long id, UpdateArticleRequest dto, String username) {
        Article article = articleRepository.findById(id)
                .orElseThrow(ArticleNotFoundException::new);

        article.validateAuthor(username);

        article.update(dto.title(), dto.content());

        return mapper.toArticleUpdateResponse(article);
    }

    // FIXME
    public ArticleDto getArticle(Long id) {
        return null;
    }

    public List<ArticlePageDto> getArticlePage(Long articleId, Integer size, ArticleSortBy sortCriteria, Order order) {
        return articleRepository.getArticleSortedBy(articleId, size, sortCriteria, order);
    }

    // FIXME
    public BookmarkedArticleDto getBookmarkedArticle(String username) {
        return null;
    }
}