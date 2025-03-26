package org.project.basicboard.article.application;

import lombok.RequiredArgsConstructor;
import org.project.basicboard.article.api.dto.request.ArticleSaveRequest;
import org.project.basicboard.article.domain.Article;
import org.project.basicboard.article.domain.repository.ArticleRepository;
import org.project.basicboard.article.exception.ArticleNotFoundException;
import org.project.basicboard.article.exception.NotAuthorizeDeleteArticleException;
import org.project.basicboard.comment.domain.repository.CommentRepository;
import org.project.basicboard.global.security.SecurityUtil;
import org.project.basicboard.user.domain.User;
import org.project.basicboard.user.domain.repository.UserRepository;
import org.project.basicboard.user.exception.UserNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ArticleService {
    private final ArticleRepository articleRepository;
    private final UserRepository userRepository;
    private final CommentRepository commentRepository;
    private final SecurityUtil securityUtil;

    @Transactional
    public Long createArticle(ArticleSaveRequest dto) {
        User user = userRepository.findByUsername(securityUtil.getCurrentUser())
                .orElseThrow(UserNotFoundException::new);

        return Article.builder()
                .title(dto.title())
                .content(dto.content())
                .user(user)
                .build()
                .getId();
    }

    @Transactional
    public void deleteArticle(Long articleId) {
        Article article = articleRepository.findById(articleId)
                .orElseThrow(ArticleNotFoundException::new);

        authorizeArticleUser(article);

        commentRepository.deleteAllByArticleId(articleId);
        articleRepository.delete(article);
    }

    private void authorizeArticleUser(Article article) {
        String currentUsername = securityUtil.getCurrentUser();

        if (!article.getUser().getUsername().equals(currentUsername))
            throw new NotAuthorizeDeleteArticleException();
    }
}
