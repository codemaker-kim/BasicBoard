package org.project.basicboard.comment.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.project.basicboard.article.domain.Article;
import org.project.basicboard.comment.exception.NotAuthorizeCommentException;
import org.project.basicboard.global.entity.BaseEntity;

@Entity
@Getter
@Table(name = "comment")
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
    private Comment(Article article, String content, String writer) {
        this.article = article;
        this.content = content;
        this.writer = writer;
    }

    public void update(String content) {
        this.content = content;
    }

    public void validateWriter(String requester) {
        if (!this.writer.equals(requester))
            throw new NotAuthorizeCommentException();
    }
}