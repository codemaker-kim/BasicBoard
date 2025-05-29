package org.project.basicboard.article.application;

import lombok.RequiredArgsConstructor;
import org.project.basicboard.article.application.dto.request.ArticleSaveServiceRequest;
import org.project.basicboard.article.application.dto.request.ArticleUpdateServiceRequest;
import org.project.basicboard.article.application.dto.response.ArticleServiceResponse;
import org.project.basicboard.article.controller.dto.response.ArticleSimpleResponse;
import org.project.basicboard.article.domain.Article;
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
    private final ArticleMapper mapper;

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

    @Transactional
    public ArticleServiceResponse getArticle(Long id, String username) {
        List<CommentDetailServiceResponse> commentList = commentRepository.findAllByArticleId(id).stream()
                .map(CommentDetailServiceResponse::from)
                .toList();

        ArticleProjectionResponse response = getArticleProcess(id, username);

        return ArticleServiceResponse.of(response, commentList);
    }

    public List<ArticleSimpleResponse> getArticlePage(int pageNumber, int size, String sortCriteria, String sortDirection) {
        return articleRepository.getArticleSortedBy(pageNumber, size, sortCriteria, sortDirection);
    }

    public List<ArticleSimpleResponse> getBookmarkedArticle(String username) {
        return mapper.toPageResponse(articleRepository.getBookmarkedArticles(username));
    }

    private Long saveArticle(Article article) {
        articleRepository.save(article);

        return article.getId();
    }

    private ArticleProjectionResponse getArticleProcess(Long id, String username) {
        // 트랜잭션 안에서 하나라도 실패한다면, 롤백될테니 괜찮을라나
        articleRepository.increaseViewCount(id);

        return articleRepository.getArticle(id, username);
    }
}