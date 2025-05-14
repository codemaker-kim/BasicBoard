package org.project.basicboard.article.repository;

import com.querydsl.core.types.Order;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.JPQLQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.project.basicboard.article.controller.dto.response.ArticlePageResponse;
import org.project.basicboard.article.domain.ArticleSortBy;
import org.project.basicboard.article.repository.dto.response.ArticleProjectionResponse;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.querydsl.core.types.Order.DESC;
import static org.project.basicboard.article.domain.QArticle.article;
import static org.project.basicboard.bookmark.domain.QBookmark.bookmark;
import static org.project.basicboard.likes.domain.QLikes.likes;

@Repository
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ArticleCustomRepositoryImpl implements ArticleCustomRepository {

    private final JPAQueryFactory queryFactory;

    @Override
    public List<ArticlePageResponse> getArticleSortedBy(Long articleId, Integer size, ArticleSortBy sortCriteria, Order order) {
        // DIP 위반
        OrderSpecifier<?> orderSpecifier = ArticleSortBy.createOrderSpecifier(order, sortCriteria);

        // 다음 페이지 존재 여부..등 커스텀 페이지 고려해보기.
        return queryFactory
                .select(Projections.constructor(ArticlePageResponse.class,
                        article.id,
                        article.title,
                        article.createdAt,
                        article.views))
                .from(article)
                .where( // 잘 짜면 넘버링도 씹가능.
                        bySortingArticle(articleId, order)
                )
                .orderBy(orderSpecifier)
                .limit(size)
                .fetch();
    }

    @Override
    public List<ArticlePageResponse> getBookmarkedArticle(String username) {
        return queryFactory
                .select(Projections.constructor(ArticlePageResponse.class,
                        article.id,
                        article.title,
                        article.createdAt,
                        article.views))
                .from(article)
                .where(bookmark.username.eq(username))
                .join(bookmark.article, article)
                .fetch();
    }

    @Override
    public ArticleProjectionResponse getArticle(Long articleId, String username) {
        // 뭐가 더 좋을라나
        return queryFactory
                .select(Projections.constructor(ArticleProjectionResponse.class,
                        article.id,
                        article.title,
                        article.content,
                        article.author,
                        article.createdAt,
                        likeCount(articleId),
                        article.views,
                        userLikeExists(articleId, username),
                        userBookmarkExists(articleId, username)
                ))
                .from(article)
                .fetchOne();
//        return queryFactory
//                .select(Projections.constructor(ArticleProjectionResponse.class,
//                        article.id,
//                        article.title,
//                        article.content,
//                        article.author,
//                        article.createdAt,
//                        likeCount(articleId),
//                        article.views,
//                        userLikeExists(articleId, username),
//                        userBookmarkExists(articleId, username)
//                        ))
//                .from(article)
//                .leftJoin(likes).on(likes.article.id.eq(articleId), likes.username.eq(username))
//                .leftJoin(bookmark).on(bookmark.article.id.eq(articleId), bookmark.username.eq(username))
//                .fetchOne();
    }
    // 나중에 caseBuilder로 처리해보기
//    private BooleanExpression userLikeExists(Long articleId, String username) {
//        return likes.article.id.eq(articleId)
//                .and(likes.username.eq(username));
//    }
//
//    private BooleanExpression userBookmarkExists(Long articleId, String username) {
//        return bookmark.article.id.eq(articleId)
//                .and(bookmark.username.eq(username));
//    }

    private JPQLQuery<Long> likeCount(Long articleId) {
        return JPAExpressions
                .select(likes.count())
                .from(likes)
                .where(likes.article.id.eq(articleId));
    }

    private BooleanExpression userLikeExists(Long articleId, String username) {
        return JPAExpressions.selectOne()
                .from(likes)
                .where(likes.article.id.eq(articleId), likes.username.eq(username))
                .exists();
    }

    private BooleanExpression userBookmarkExists(Long articleId, String username) {
        return JPAExpressions.selectOne()
                .from(bookmark)
                .where(bookmark.article.id.eq(articleId), bookmark.username.eq(username))
                .exists();
    }

    private BooleanExpression bySortingArticle(Long articleId, Order order) {
        if (articleId == null) {
            return null;
        }

        return getBooleanExpression(articleId, order);
    }

    private BooleanExpression getBooleanExpression(Long articleId, Order order) {
        return order == DESC ? article.id.lt(articleId) : article.id.gt(articleId);
    }
}