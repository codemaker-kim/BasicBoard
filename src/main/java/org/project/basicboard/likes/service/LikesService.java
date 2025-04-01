package org.project.basicboard.likes.service;

import lombok.RequiredArgsConstructor;
import org.project.basicboard.article.domain.Article;
import org.project.basicboard.article.domain.repository.ArticleRepository;
import org.project.basicboard.article.exception.ArticleNotFoundException;
import org.project.basicboard.global.security.SecurityUtil;
import org.project.basicboard.likes.domain.Likes;
import org.project.basicboard.likes.domain.repository.LikesRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class LikesService {

    private final LikesRepository likesRepository;
    private final ArticleRepository articleRepository;

    public void createOrDeleteLike(Long articleId) {
        Article article = articleRepository.findById(articleId)
                .orElseThrow(ArticleNotFoundException::new);

        createOrDeleteProcess(article);
    }

    private void createOrDeleteProcess(Article article) {
        String currentUser = SecurityUtil.getCurrentUser();

        Optional<Likes> like = likesRepository.findByUsernameAndArticleId(currentUser, article.getId());

        if (like.isPresent()) {
            likesRepository.delete(like.get());
            article.decreaseLikeCount();
            return;
        }

        Likes newLike = Likes.builder()
                .article(article)
                .username(currentUser)
                .build();

        article.increaseLikeCount();
        likesRepository.save(newLike);
    }
}