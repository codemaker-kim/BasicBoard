package org.project.basicboard.article.application;

import org.project.basicboard.article.api.dto.response.ArticleDto;
import org.project.basicboard.article.api.dto.response.ArticleUpdateResponse;
import org.project.basicboard.article.api.dto.response.LikeAndBookmarkedDto;
import org.project.basicboard.article.domain.Article;
import org.project.basicboard.comment.api.dto.response.CommentInfoDto;
import org.project.basicboard.comment.domain.Comment;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ArticleMapper {

    public ArticleUpdateResponse toArticleUpdateResponse(Article article) {
        return ArticleUpdateResponse.from(article);
    }

    public ArticleDto toArticleDto(Article article,
                                   List<Comment> comments,
                                   LikeAndBookmarkedDto likeAndBookmarked) {

        List<CommentInfoDto> commentInfoList = getCommentInfoList(comments);

        return ArticleDto.from(article, commentInfoList, likeAndBookmarked);
    }

    private List<CommentInfoDto> getCommentInfoList(List<Comment> comments) {
        return comments.stream()
                .map(CommentInfoDto::from)
                .toList();
    }
}