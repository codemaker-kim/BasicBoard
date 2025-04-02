package org.project.basicboard.article.domain.repository;

import com.querydsl.core.types.Order;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.core.types.dsl.PathBuilder;
import com.querydsl.core.types.dsl.StringPath;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.project.basicboard.article.api.dto.response.ArticlePageDto;
import org.project.basicboard.article.domain.Article;
import org.project.basicboard.comment.api.dto.response.CommentInfoDto;
import org.project.basicboard.comment.domain.QComment;
import org.project.basicboard.user.domain.QUser;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.project.basicboard.article.domain.QArticle.article;
import static org.project.basicboard.bookmark.domain.QBookmark.bookmark;
import static org.project.basicboard.comment.domain.QComment.comment;
import static org.project.basicboard.user.domain.QUser.user;

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
}