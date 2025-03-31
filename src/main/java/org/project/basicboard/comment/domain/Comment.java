package org.project.basicboard.comment.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.project.basicboard.article.domain.Article;
import org.project.basicboard.global.entity.BaseEntity;

@Entity
@Table(name = "comment")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Comment extends BaseEntity {

    @Column(name = "content", nullable = false)
    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "article_id")
    private Article article;

    @Column(nullable = false)
    private String writer;

    @Builder
    Comment(Article article, String content, String writer) {
        this.content = content;
        this.writer = writer;
        this.article = article;
    }

    public void update(String content) {
        this.content = content;
    }
}
