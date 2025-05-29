package org.project.basicboard.article.repository;

import com.querydsl.core.types.Order;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.JPQLQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.project.basicboard.article.controller.dto.response.ArticleSimpleResponse;
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
    public List<ArticleSimpleResponse> getArticleSortedBy(int pageNumber, int size, final String sortCriteria, final String sortDirection) {
        OrderSpecifier<?> orderSpecifier = getOrderSpecifier(sortCriteria, sortDirection);

        return queryFactory
                .select(Projections.constructor(ArticleSimpleResponse.class,
                        article.id,
                        article.title,
                        article.createdAt,
                        article.views))
                .from(article)
                .where(
                    // todo: 페이지 번호
                )
                .orderBy(orderSpecifier)
                .limit(size)
                .fetch();
    }

    @Override
    public ArticleProjectionResponse getArticle(Long articleId, String username) {
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

    }

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

    private OrderSpecifier<?> getOrderSpecifier(String sortCriteria, String sortDirection) {
        Order order = Order.valueOf(sortDirection);

        return new OrderSpecifier<>(order, Expressions.stringPath(article, sortCriteria));
    }
}