package org.project.basicboard.comment.application;

import lombok.RequiredArgsConstructor;
import org.project.basicboard.article.domain.Article;
import org.project.basicboard.article.repository.ArticleRepository;
import org.project.basicboard.article.exception.ArticleNotFoundException;
import org.project.basicboard.comment.application.dto.request.AddCommentServiceRequest;
import org.project.basicboard.comment.application.dto.request.DeleteCommentServiceRequest;
import org.project.basicboard.comment.application.dto.request.UpdateCommentServiceRequest;
import org.project.basicboard.comment.controller.dto.request.AddCommentRequest;
import org.project.basicboard.comment.controller.dto.request.UpdateCommentRequest;
import org.project.basicboard.comment.controller.dto.response.CommentInfoResponse;
import org.project.basicboard.comment.domain.Comment;
import org.project.basicboard.comment.repository.CommentRepository;
import org.project.basicboard.comment.exception.CommentNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;
    private final ArticleRepository articleRepository;
    private final CommentMapper mapper;

    public Long addComment(AddCommentServiceRequest request) {
        Comment comment = makeComment(request.id(), request.content(), request.username());

        commentRepository.save(comment);

        return comment.getId();
    }

    @Transactional
    public void updateComment(UpdateCommentServiceRequest request) {
        Comment comment = commentRepository.findById(request.id())
                .orElseThrow(CommentNotFoundException::new);

        comment.validateWriter(request.username());

        comment.update(request.content());
    }

    public void deleteComment(DeleteCommentServiceRequest request) {
        Comment comment = commentRepository.findById(request.id())
                .orElseThrow(CommentNotFoundException::new);

        comment.validateWriter(request.username());

        commentRepository.delete(comment);
    }

    @Transactional(readOnly = true)
    public List<CommentInfoResponse> findAllCommentInArticle(Long articleId) {
        List<Comment> comments = commentRepository.findAllByArticleId(articleId);

        return mapper.toCommentInfoResponse(comments);
    }

    private Comment makeComment(Long id, String content, String username) {
        Article article = articleRepository.findById(id)
                .orElseThrow(ArticleNotFoundException::new);

        return Comment.builder()
                .content(content)
                .article(article)
                .writer(username)
                .build();
    }
}
