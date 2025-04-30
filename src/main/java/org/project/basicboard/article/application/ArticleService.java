package org.project.basicboard.article.application;

import com.querydsl.core.types.Order;
import lombok.RequiredArgsConstructor;
import org.project.basicboard.article.application.dto.request.ArticleSaveServiceRequest;
import org.project.basicboard.article.application.dto.request.ArticleUpdateServiceRequest;
import org.project.basicboard.article.controller.dto.response.ArticleDto;
import org.project.basicboard.article.controller.dto.response.ArticlePageDto;
import org.project.basicboard.article.controller.dto.response.BookmarkedArticleDto;
import org.project.basicboard.article.domain.Article;
import org.project.basicboard.article.domain.ArticleSortBy;
import org.project.basicboard.article.exception.ArticleNotFoundException;
import org.project.basicboard.article.repository.ArticleRepository;
import org.project.basicboard.comment.domain.repository.CommentRepository;
import org.project.basicboard.likes.repository.LikesRepository;
import org.project.basicboard.user.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ArticleService {

    private final ArticleRepository articleRepository;
    private final CommentRepository commentRepository;
    private final UserRepository userRepository;
    private final LikesRepository likesRepository;
    private final ArticleMapper mapper;

    public Long createArticle(String username, ArticleSaveServiceRequest dto) {
        Article article = createArticleEntity(dto, username);
        return saveArticle(article);
    }

    public void deleteArticle(Long articleId, String username) {
        Article article = articleRepository.findById(articleId)
                .orElseThrow(ArticleNotFoundException::new);

        article.validateAuthor(username);

        commentRepository.deleteAllByArticleId(articleId);
        articleRepository.delete(article);
    }

    @Transactional
    public void update(Long id, ArticleUpdateServiceRequest dto, String username) {
        Article article = articleRepository.findById(id)
                .orElseThrow(ArticleNotFoundException::new);

        article.validateAuthor(username);

        article.update(dto.title(), dto.content());
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

    private Article createArticleEntity(ArticleSaveServiceRequest dto, String username) {
        return Article.createOf(dto.title(), dto.content(), username);
    }

    private Long saveArticle(Article article) {
        articleRepository.save(article);

        return article.getId();
    }
}