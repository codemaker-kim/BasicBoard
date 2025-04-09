package org.project.basicboard.article.domain.repository;

import com.querydsl.core.types.ExpressionUtils;
import com.querydsl.core.types.Order;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.project.basicboard.article.api.dto.response.ArticlePageDto;
import org.project.basicboard.article.api.dto.response.LikeAndBookmarkedDto;
import org.project.basicboard.comment.api.dto.response.CommentInfoDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static org.project.basicboard.article.domain.QArticle.article;
import static org.project.basicboard.bookmark.domain.QBookmark.bookmark;
import static org.project.basicboard.comment.domain.QComment.comment;
import static org.project.basicboard.likes.domain.QLikes.likes;

@Repository
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ArticleCustomRepositoryImpl implements ArticleCustomRepository {

    private final JPAQueryFactory queryFactory;

    @Override
    public Page<ArticlePageDto> getArticleSortedBy(Pageable pageable, String sortCriteria) {

        OrderSpecifier<?> orderSpecifiers = new OrderSpecifier<>(Order.DESC, Expressions.stringPath(article, sortCriteria));

        List<ArticlePageDto> results = queryFactory
                .select(Projections.constructor(ArticlePageDto.class,
                        article.id,
                        article.title,
                        article.createdAt,
                        article.views))
                .from(article)
                .orderBy(orderSpecifiers)
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

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
    public List<CommentInfoDto> getArticleComments(Long articleId) {
        return queryFactory
                .select(Projections.constructor(CommentInfoDto.class,
                        comment.id,
                        comment.writer,
                        comment.content
                ))
                .from(comment)
                .where(article.id.eq(articleId))
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