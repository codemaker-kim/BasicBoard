package org.project.basicboard.bookmark.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.project.basicboard.article.domain.Article;
import org.project.basicboard.global.entity.BaseEntity;
import org.project.basicboard.global.entity.BaseTimeEntity;
import org.project.basicboard.user.domain.User;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Entity
@Getter
@Table(name = "bookmarks")
@EntityListeners(AuditingEntityListener.class)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Bookmark {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @CreatedDate
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "article_id")
    private Article article;

    @Column(nullable = false)
    private String username;

    @Builder
    private Bookmark(Article article, String username) {
        this.article = article;
        this.username = username;
    }

    public static Bookmark create(Article article, String username) {
        return Bookmark.builder()
                .article(article)
                .username(username)
                .build();
    }
}