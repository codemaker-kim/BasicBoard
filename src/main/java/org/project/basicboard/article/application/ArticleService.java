package org.project.basicboard.article.application;

import com.querydsl.core.types.Order;
import lombok.RequiredArgsConstructor;
import org.project.basicboard.article.application.dto.request.ArticleSaveServiceRequest;
import org.project.basicboard.article.application.dto.request.ArticleUpdateServiceRequest;
import org.project.basicboard.article.controller.dto.response.ArticleDto;
import org.project.basicboard.article.controller.dto.response.ArticlePageDto;
import org.project.basicboard.article.domain.Article;
import org.project.basicboard.article.domain.ArticleSortBy;
import org.project.basicboard.article.exception.ArticleNotFoundException;
import org.project.basicboard.article.repository.ArticleRepository;
import org.project.basicboard.bookmark.repository.BookmarkRepository;
import org.project.basicboard.comment.api.dto.response.CommentInfoDto;
import org.project.basicboard.comment.domain.repository.CommentRepository;
import org.project.basicboard.likes.repository.LikesRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ArticleService {

    private final ArticleRepository articleRepository;
    private final CommentRepository commentRepository;
    private final LikesRepository likesRepository;
    private final BookmarkRepository bookmarkRepository;
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

    @Transactional
    public ArticleDto getArticle(Long id, String username) {
        Article article = findArticleById(id);
        boolean bookmarked = isBookmarkedByUser(username, id);
        boolean liked = isLikedByUser(username, id);
        List<CommentInfoDto> comments = getCommentsByArticleId(id);

        article.increaseViews();

        return assembleArticleDto(article, bookmarked, liked, comments);
    }

    public List<ArticlePageDto> getArticlePage(Long articleId, Integer size, ArticleSortBy sortCriteria, Order order) {
        return articleRepository.getArticleSortedBy(articleId, size, sortCriteria, order);
    }

    public List<ArticlePageDto> getBookmarkedArticle(String username) {
        return articleRepository.getBookmarkedArticle(username);
    }


    private Article findArticleById(Long id) {
        return articleRepository.findById(id)
                .orElseThrow(ArticleNotFoundException::new);
    }

    private boolean isBookmarkedByUser(String username, Long articleId) {
        return bookmarkRepository.existByUsernameAndArticleId(username, articleId);
    }

    private boolean isLikedByUser(String username, Long articleId) {
        return likesRepository.existsByUsernameAndArticleId(username, articleId);
    }

    private List<CommentInfoDto> getCommentsByArticleId(Long articleId) {
        return commentRepository.findCommentInfoByArticleId(articleId);
    }

    private ArticleDto assembleArticleDto(Article article,
                                          boolean bookmarked,
                                          boolean liked,
                                          List<CommentInfoDto> comments) {
        return ArticleDto.builder()
                .id(article.getId())
                .title(article.getTitle())
                .content(article.getContent())
                .author(article.getAuthor())
                .createdAt(article.getCreatedAt())
                .likeCount(article.getLikeCount())
                .views(article.getViews())
                .like(liked)
                .bookmarked(bookmarked)
                .comments(comments)
                .build();
    }

    private Article createArticleEntity(ArticleSaveServiceRequest dto, String username) {
        return Article.createOf(dto.title(), dto.content(), username);
    }

    private Long saveArticle(Article article) {
        articleRepository.save(article);

        return article.getId();
    }
}