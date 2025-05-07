package org.project.basicboard.likes.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.StaleObjectStateException;
import org.project.basicboard.article.domain.Article;
import org.project.basicboard.article.exception.ArticleNotFoundException;
import org.project.basicboard.article.repository.ArticleRepository;
import org.project.basicboard.likes.domain.Likes;
import org.project.basicboard.likes.repository.LikesRepository;
import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class LikesService {

    private final LikesRepository likesRepository;
    private final ArticleRepository articleRepository;
    private final LikeHelper likeHelper;

    @Transactional
    public void createLike(Long articleId, String username) {
        Article targetArticle = findArticle(articleId);

        tryLike(targetArticle, username);
    }

    @Transactional
    public void deleteLike(Long articleId, String username) {
        Article targetArticle = findArticle(articleId);

        likesRepository.deleteByArticleIdAndUsername(articleId, username);

        targetArticle.decreaseLikeCount();
    }

    private Article findArticle(Long articleId) {
        return articleRepository.findById(articleId)
                .orElseThrow(ArticleNotFoundException::new);
    }

    private void tryLike(Article targetArticle, String username) {
        int retries = 0;
        boolean success = false;

        while (!success) {
            try {
                likeHelper.safeIncreaseLike(targetArticle.getId(), username);
                success = true;
                return;
            } catch(ObjectOptimisticLockingFailureException e) {
                retries++;
                log.warn("좋아요 증가 중 동시성 이슈 발생, {}회째 재시도 중", retries);
            }
        }
    }
}