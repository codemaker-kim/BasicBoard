package org.project.basicboard.likes.service;

import lombok.RequiredArgsConstructor;
import org.project.basicboard.article.domain.Article;
import org.project.basicboard.article.exception.ArticleNotFoundException;
import org.project.basicboard.article.repository.ArticleRepository;
import org.project.basicboard.likes.domain.Likes;
import org.project.basicboard.likes.exception.LikesAlreadyExistException;
import org.project.basicboard.likes.exception.LikesNotFoundException;
import org.project.basicboard.likes.repository.LikesRepository;
import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class LikesService {

    private final LikesRepository likesRepository;
    private final ArticleRepository articleRepository;

    public void createLike(Long articleId, String username) {
        Likes like = newLike(articleId, username);

        likesRepository.save(like);
    }

    public void deleteLike(Long articleId, String username) {
        Likes like = likesRepository.findByArticleIdAndUsername(articleId, username)
                .orElseThrow(LikesNotFoundException::new);

        likesRepository.delete(like);
    }

    private Likes newLike(Long articleId, String username) {
        Article targetArticle = articleRepository.findById(articleId)
                .orElseThrow(ArticleNotFoundException::new);

        LikeExistCheck(articleId, username);

        return Likes.of(targetArticle, username);
    }

    private void LikeExistCheck(Long articleId, String username) {
        if(likesRepository.existsByArticleIdAndUsername(articleId, username))
            throw new LikesAlreadyExistException();
    }
}