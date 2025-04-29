package org.project.basicboard.article.repository;

import com.querydsl.core.types.ExpressionUtils;
import com.querydsl.core.types.Order;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.project.basicboard.article.controller.dto.response.ArticlePageDto;
import org.project.basicboard.article.controller.dto.response.LikeAndBookmarkedDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static org.project.basicboard.article.domain.QArticle.article;
import static org.project.basicboard.bookmark.domain.QBookmark.bookmark;
import static org.project.basicboard.likes.domain.QLikes.likes;

@Repository
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ArticleCustomRepositoryImpl implements ArticleCustomRepository {

    private final JPAQueryFactory queryFactory;

    // todo: 오름차순 정렬 기능 추가해보기
    // todo: offset -> 성능 좀 별로
    // todo: QueryDsl 자체가, 동적 쿼리를 처리하기 위한 그거인데..
    // FIXME: 사용자 입력마다 달라지지 않는 쿼리를 굳이 여기 넣어야 하는가? 고민해보자.

    // 굳이 Pageable을 사용할 이유가 없음.
    @Override
    public Page<ArticlePageDto> getArticleSortedBy(Pageable pageable, String sortCriteria) {

        OrderSpecifier<?> orderSpecifiers = new OrderSpecifier<>(Order.DESC, Expressions.stringPath(article, sortCriteria));

        List<ArticlePageDto> results = queryFactory
                .select(Projections.constructor(ArticlePageDto.class,
                        article.id,
                        article.title,
                        article.createdAt,
                        article.views))
                .from(article) // where 문에서 한 번 체크하자. ()
                .orderBy(orderSpecifiers)
                .offset(pageable.getOffset()) // 이거 쓰지말구 다른 방법 찾아보자
                .limit(pageable.getPageSize())
                .fetch();

        // 이 카운트 쿼리는 메서드로 분리해도 될 거 같음.
        long total = Optional.ofNullable(queryFactory
                        .select(article.count())
                        .from(article)
                        .fetchOne())
                .orElse(0L);

        return new PageImpl<>(results, pageable, total);
    }

    @Override
    public List<ArticlePageDto> getArticleBookmarked(String username) {
        return queryFactory
                .select(Projections.constructor(ArticlePageDto.class,
                        article.id,
                        article.title,
                        article.createdAt,
                        article.views
                ))
                .from(article)
                .join(bookmark).on(bookmark.article.eq(article))
                .where(article.author.eq(username))
                .fetch();
    }

    @Override
    public LikeAndBookmarkedDto isArticleLikeAndBookmarked(Long articleId, String username) {
        return queryFactory
                .select(Projections.constructor(LikeAndBookmarkedDto.class,
                        ExpressionUtils.as(
                                JPAExpressions.selectOne()
                                        .from(likes)
                                        .where(likes.article.id.eq(articleId),
                                                likes.username.eq(username))
                                        .exists(),
                                "like"
                        ),
                        ExpressionUtils.as(
                                JPAExpressions.selectOne()
                                        .from(bookmark)
                                        .where(bookmark.article.id.eq(articleId),
                                                bookmark.username.eq(username))
                                        .exists(),
                                "bookmarked"
                        )
                ))
                .from(article)
                .where(article.id.eq(articleId))
                .fetchOne();
    }
}