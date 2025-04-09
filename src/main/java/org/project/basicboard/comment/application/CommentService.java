package org.project.basicboard.comment.application;

import lombok.RequiredArgsConstructor;
import org.project.basicboard.article.domain.Article;
import org.project.basicboard.article.domain.repository.ArticleRepository;
import org.project.basicboard.article.exception.ArticleNotFoundException;
import org.project.basicboard.comment.api.dto.request.AddCommentRequest;
import org.project.basicboard.comment.api.dto.request.UpdateCommentRequest;
import org.project.basicboard.comment.api.dto.response.CommentResponse;
import org.project.basicboard.comment.api.dto.response.ArticleCommentResponse;
import org.project.basicboard.comment.api.dto.response.CommentInfoDto;
import org.project.basicboard.comment.domain.Comment;
import org.project.basicboard.comment.domain.repository.CommentRepository;
import org.project.basicboard.comment.exception.CommentNotFoundException;
import org.project.basicboard.global.security.SecurityUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;
    private final ArticleRepository articleRepository;
    private final CommentMapper mapper;

    public CommentResponse addComment(AddCommentRequest dto) {
        Comment comment = makeComment(dto);

        commentRepository.save(comment);

        return mapper.toCommentResponse(comment);
    }

    public CommentResponse updateComment(Long commentId, UpdateCommentRequest dto) {
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(CommentNotFoundException::new);

        String currentUser = SecurityUtil.getCurrentUser();
        comment.validateWriter(currentUser);
        comment.update(dto.content());

        return mapper.toCommentResponse(comment);
    }

    public void deleteComment(Long commentId) {
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(CommentNotFoundException::new);

        String currentUser = SecurityUtil.getCurrentUser();
        comment.validateWriter(currentUser);

        commentRepository.delete(comment);
    }

    @Transactional(readOnly = true)
    public ArticleCommentResponse findAllCommentInArticle(Long articleId) {
        List<Comment> comments = commentRepository.findAllByArticleId(articleId);

        return mapper.toArticleCommentResponse(comments);
    }

    private Comment makeComment(AddCommentRequest dto) {
        String currentUser = SecurityUtil.getCurrentUser();

        Article article = articleRepository.findById(dto.articleId())
                .orElseThrow(ArticleNotFoundException::new);

        return Comment.builder()
                .content(dto.content())
                .article(article)
                .writer(currentUser)
                .build();
    }
}
