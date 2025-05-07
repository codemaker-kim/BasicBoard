package org.project.basicboard.likes.service;

import lombok.RequiredArgsConstructor;
import org.project.basicboard.article.domain.Article;
import org.project.basicboard.article.exception.ArticleNotFoundException;
import org.project.basicboard.article.repository.ArticleRepository;
import org.project.basicboard.likes.domain.Likes;
import org.project.basicboard.likes.repository.LikesRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class LikeHelper {

    private final ArticleRepository articleRepository;
    private final LikesRepository likesRepository;

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void safeIncreaseLike(Long articleId, final String username) {
        Article article = articleRepository.findById(articleId)
                .orElseThrow(ArticleNotFoundException::new);

        Likes like = Likes.builder()
                .article(article)
                .username(username)
                .build();

        likesRepository.save(like);

        article.increaseLikeCount();
    }
}
