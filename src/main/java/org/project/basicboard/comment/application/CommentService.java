package org.project.basicboard.comment.application;

import lombok.RequiredArgsConstructor;
import org.project.basicboard.article.domain.Article;
import org.project.basicboard.article.exception.ArticleNotFoundException;
import org.project.basicboard.article.repository.ArticleRepository;
import org.project.basicboard.comment.application.dto.response.CommentDetailServiceResponse;
import org.project.basicboard.comment.application.dto.response.CommentInfoServiceResponse;
import org.project.basicboard.comment.application.dto.request.AddCommentServiceRequest;
import org.project.basicboard.comment.application.dto.request.UpdateCommentServiceRequest;
import org.project.basicboard.comment.domain.Comment;
import org.project.basicboard.comment.exception.CommentNotFoundException;
import org.project.basicboard.comment.repository.CommentRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;
    private final ArticleRepository articleRepository;

    public Long addComment(AddCommentServiceRequest request) {
        Comment comment = makeComment(request.articleId(), request.content(), request.username());

        return commentRepository.save(comment)
                .getId();
    }

    @Transactional
    public void updateComment(UpdateCommentServiceRequest request) {
        Comment comment = commentRepository.findById(request.id())
                .orElseThrow(CommentNotFoundException::new);

        comment.validateWriter(request.username());

        comment.update(request.content());
    }

    public void deleteComment(Long id, String username) {
        Comment comment = commentRepository.findById(id)
                .orElseThrow(CommentNotFoundException::new);

        comment.validateWriter(username);

        commentRepository.delete(comment);
    }

    @Transactional(readOnly = true)
    public CommentInfoServiceResponse findAllCommentInArticle(Long articleId) {
        List<CommentDetailServiceResponse> comments = commentRepository.findAllByArticleId(articleId).stream()
                .map(CommentDetailServiceResponse::from)
                .toList();

        return CommentInfoServiceResponse.of(comments);
    }

    private Comment makeComment(Long articleId, String content, String username) {
        Article article = articleRepository.findById(articleId)
                .orElseThrow(ArticleNotFoundException::new);

        return Comment.builder()
                .content(content)
                .article(article)
                .writer(username)
                .build();
    }
}