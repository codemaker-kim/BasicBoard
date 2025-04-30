package org.project.basicboard.article.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Version;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.project.basicboard.article.exception.NotAuthorizeArticleException;
import org.project.basicboard.article.exception.TitleLengthException;
import org.project.basicboard.global.entity.BaseEntity;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Article extends BaseEntity {

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String content;

    private Integer views;

    private Integer likeCount;

    @Column(nullable = false)
    private String author;

    @Builder
    private Article(String title, String content, String author) {
        this.title = title;
        this.content = content;
        this.views = 0;
        this.likeCount = 0;
        this.author = author;
    }

    public void update(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public void increaseViews() {
        this.views++;
    }

    public void increaseLikeCount() {
        this.likeCount++;
    }

    public void decreaseLikeCount() {
        this.likeCount--;
    }

    public void validateAuthor(String requester) {
        if (!requester.equals(author)) {
            throw new NotAuthorizeArticleException();
        }
    }

    public static Article createOf(String title, String content, String author) {
        validateTitle(title);

        return Article.builder()
                .title(title)
                .content(content)
                .author(author)
                .build();
    }

    private static void validateTitle(String title) {
        if (title.length() > 100) {
            throw new TitleLengthException();
        }
    }
}