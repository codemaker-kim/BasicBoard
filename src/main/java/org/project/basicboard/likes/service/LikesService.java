package org.project.basicboard.likes.service;

import lombok.RequiredArgsConstructor;
import org.project.basicboard.article.domain.Article;
import org.project.basicboard.article.repository.ArticleRepository;
import org.project.basicboard.article.exception.ArticleNotFoundException;
import org.project.basicboard.global.security.SecurityUtil;
import org.project.basicboard.likes.domain.Likes;
import org.project.basicboard.likes.repository.LikesRepository;
import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class LikesService {

    private final LikesRepository likesRepository;
    private final ArticleRepository articleRepository;

    public void createLike(Long articleId, String username) {
        Article targetArticle = findArticle(articleId);

        Likes like = Likes.builder()
                .article(targetArticle)
                .username(username)
                .build();

        likesRepository.save(like);

        targetArticle.increaseLikeCount();
    }


    public void deleteLike(Long articleId, String username) {
        Article targetArticle = findArticle(articleId);

        likesRepository.deleteByArticleIdAndUsername(articleId, username);

        targetArticle.decreaseLikeCount();
    }

    private Article findArticle(Long articleId) {
        return articleRepository.findById(articleId)
                .orElseThrow(ArticleNotFoundException::new);
    }
}