package org.project.basicboard.article.repository;

import com.querydsl.core.types.Order;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.project.basicboard.article.controller.dto.response.ArticlePageDto;
import org.project.basicboard.article.domain.ArticleSortBy;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.querydsl.core.types.Order.DESC;
import static org.project.basicboard.article.domain.QArticle.article;

@Repository
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ArticleCustomRepositoryImpl implements ArticleCustomRepository {

    private final JPAQueryFactory queryFactory;

    @Override
    public List<ArticlePageDto> getArticleSortedBy(Long articleId, Integer size, ArticleSortBy sortCriteria, Order order) {

        OrderSpecifier<?> orderSpecifier = ArticleSortBy.createOrderSpecifier(order, sortCriteria);

        return queryFactory
                .select(Projections.constructor(ArticlePageDto.class,
                        article.id,
                        article.title,
                        article.createdAt,
                        article.views))
                .from(article)
                .where(
                        bySortingArticle(articleId, order)
                )
                .orderBy(orderSpecifier)
                .limit(size)
                .fetch();
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