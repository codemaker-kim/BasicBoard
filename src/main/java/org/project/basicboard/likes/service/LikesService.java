package org.project.basicboard.likes.service;

import lombok.RequiredArgsConstructor;
import org.project.basicboard.article.domain.Article;
import org.project.basicboard.article.domain.repository.ArticleRepository;
import org.project.basicboard.article.exception.ArticleNotFoundException;
import org.project.basicboard.global.security.SecurityUtil;
import org.project.basicboard.likes.domain.Likes;
import org.project.basicboard.likes.domain.repository.LikesRepository;
import org.project.basicboard.user.domain.User;
import org.project.basicboard.user.domain.repository.UserRepository;
import org.project.basicboard.user.exception.UserNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class LikesService {

    private final LikesRepository likesRepository;
    private final UserRepository userRepository;
    private final ArticleRepository articleRepository;
    private final SecurityUtil securityUtil;

    public void createOrDeleteLike(Long articleId) {
        User user = userRepository.findByUsername(securityUtil.getCurrentUser())
                .orElseThrow(UserNotFoundException::new);

        Article article = articleRepository.findById(articleId)
                .orElseThrow(ArticleNotFoundException::new);

        createOrDeleteProcess(user, article);
    }

    private void createOrDeleteProcess(User user, Article article) {
        if (likesRepository.existsByUserIdAndArticleId(user.getId(), article.getId())) {
            Likes like = likesRepository.findByUserIdAndArticleId(user.getId(), article.getId()).get();
            likesRepository.delete(like);

            article.decreaseLikeCount();
        } else {
            Likes likes = Likes.builder()
                    .user(user)
                    .article(article)
                    .build();

            likesRepository.save(likes);
            article.increaseLikeCount();
        }
    }
}