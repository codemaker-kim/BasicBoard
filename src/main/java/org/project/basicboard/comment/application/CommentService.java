package org.project.basicboard.comment.application;

import lombok.RequiredArgsConstructor;
import org.project.basicboard.article.domain.Article;
import org.project.basicboard.article.domain.repository.ArticleRepository;
import org.project.basicboard.article.exception.ArticleNotFoundException;
import org.project.basicboard.comment.api.dto.request.AddCommentRequest;
import org.project.basicboard.comment.api.dto.request.UpdateCommentRequest;
import org.project.basicboard.comment.api.dto.response.AddCommentResponse;
import org.project.basicboard.comment.api.dto.response.UpdateCommentResponse;
import org.project.basicboard.comment.domain.Comment;
import org.project.basicboard.comment.domain.repository.CommentRepository;
import org.project.basicboard.comment.exception.CommentNotFoundException;
import org.project.basicboard.comment.exception.NotAuthorizeCommentException;
import org.project.basicboard.global.security.SecurityUtil;
import org.project.basicboard.user.domain.User;
import org.project.basicboard.user.domain.repository.UserRepository;
import org.project.basicboard.user.exception.UserNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;
    private final ArticleRepository articleRepository;
    private final UserRepository userRepository;
    private final SecurityUtil securityUtil;

    @Transactional
    public AddCommentResponse addComment(AddCommentRequest dto) {
        Comment comment = makeComment(dto);

        commentRepository.save(comment);

        return AddCommentResponse.from(comment);
    }

    @Transactional
    public UpdateCommentResponse updateComment(Long commentId, UpdateCommentRequest dto) {
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(CommentNotFoundException::new);

        authorizeCommentUser(comment);
        comment.update(dto.content());

        return UpdateCommentResponse.from(comment);
    }

    @Transactional
    public void deleteComment(Long commentId) {
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(CommentNotFoundException::new);

        authorizeCommentUser(comment);

        commentRepository.delete(comment);
    }

    private Comment makeComment(AddCommentRequest dto) {
        User user = userRepository.findByUsername(securityUtil.getCurrentUser())
                .orElseThrow(UserNotFoundException::new);

        Article article = articleRepository.findById(dto.articleId())
                .orElseThrow(ArticleNotFoundException::new);

        return Comment.builder()
                .content(dto.content())
                .user(user)
                .article(article)
                .build();
    }

    private void authorizeCommentUser(Comment comment) {
        String currentUsername = securityUtil.getCurrentUser();

        if (!comment.getUser().getUsername().equals(currentUsername))
            throw new NotAuthorizeCommentException();
    }
}
