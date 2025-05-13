package org.project.basicboard.article.application;

import com.querydsl.core.types.Order;
import lombok.RequiredArgsConstructor;
import org.project.basicboard.article.application.dto.request.ArticleSaveServiceRequest;
import org.project.basicboard.article.application.dto.request.ArticleUpdateServiceRequest;
import org.project.basicboard.article.application.dto.response.ArticleServiceResponse;
import org.project.basicboard.article.controller.dto.response.ArticlePageResponse;
import org.project.basicboard.article.domain.Article;
import org.project.basicboard.article.domain.ArticleSortBy;
import org.project.basicboard.article.exception.ArticleNotFoundException;
import org.project.basicboard.article.repository.ArticleRepository;
import org.project.basicboard.article.repository.dto.response.ArticleProjectionResponse;
import org.project.basicboard.comment.application.dto.response.CommentDetailServiceResponse;
import org.project.basicboard.comment.repository.CommentRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ArticleService {

    private final ArticleRepository articleRepository;
    private final CommentRepository commentRepository;

    public Long createArticle(String username, ArticleSaveServiceRequest dto) {
        Article article = Article.createOf(dto.title(), dto.content(), username);

        return saveArticle(article);
    }

    public void deleteArticle(Long id, String username) {
        Article article = articleRepository.findById(id)
                .orElseThrow(ArticleNotFoundException::new);

        article.validateAuthor(username);

        commentRepository.deleteAllByArticleId(id);
        articleRepository.delete(article);
    }

    @Transactional
    public void update(Long id, ArticleUpdateServiceRequest dto, String username) {
        Article article = articleRepository.findById(id)
                .orElseThrow(ArticleNotFoundException::new);

        article.validateAuthor(username);

        article.update(dto.title(), dto.content());
    }

    // TODO: 조회수 우짜노
    @Transactional
    public ArticleServiceResponse getArticle(Long id, String username) {
        List<CommentDetailServiceResponse> commentList = commentRepository.findAllByArticleId(id).stream()
                .map(CommentDetailServiceResponse::from)
                .toList();

        ArticleProjectionResponse response = getArticleProcess(id, username);

        return ArticleServiceResponse.of(response, commentList);
    }

    private ArticleProjectionResponse getArticleProcess(Long id, String username) {
        // 트랜잭션 안에서 하나라도 실패한다면, 롤백될테니 괜찮을라나
        articleRepository.increaseViewCount(id);

        return articleRepository.getArticle(id, username);
    }

    // FIXME: 이것도 articleSortBy랑 Order 그대로 넘어가는게 부자연스러움.
    public List<ArticlePageResponse> getArticlePage(Long id, Integer size, ArticleSortBy sortCriteria, Order order) {
        return articleRepository.getArticleSortedBy(id, size, sortCriteria, order);
    }

    public List<ArticlePageResponse> getBookmarkedArticle(String username) {
        return articleRepository.getBookmarkedArticle(username);
    }

    private Long saveArticle(Article article) {
        articleRepository.save(article);

        return article.getId();
    }
}