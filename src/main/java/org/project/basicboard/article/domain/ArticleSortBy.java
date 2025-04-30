package org.project.basicboard.article.domain;

import com.querydsl.core.types.Order;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.dsl.Expressions;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import static org.project.basicboard.article.domain.QArticle.article;

@Getter
@RequiredArgsConstructor
public enum ArticleSortBy {
    CREATE_AT("createdAt"),
    ID("id"),
    VIEWS("views"),
    TITLE("title");

    final String value;

    public static OrderSpecifier<?> createOrderSpecifier(Order order, ArticleSortBy sortBy) {
        return new OrderSpecifier<>(order, Expressions.stringPath(article, sortBy.value));
    }
}