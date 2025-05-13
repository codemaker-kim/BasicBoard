package org.project.basicboard.comment.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.project.basicboard.comment.controller.dto.request.AddCommentRequest;
import org.project.basicboard.comment.controller.dto.request.UpdateCommentRequest;
import org.project.basicboard.comment.controller.dto.response.CommentInfoResponse;
import org.springframework.http.ResponseEntity;

@Tag(name = "[댓글 API]", description = "댓글 관련 API")
public interface CommentDocs {

    @Operation(summary = "게시글 내 댓글 조회.", description = "게시글 안에 있는 댓글을 모두 조회합니다.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "댓글 목록 조회 성공",
                    content = @Content(schema = @Schema(implementation = CommentInfoResponse.class))),
                    @ApiResponse(responseCode = "400", description = "잘못된 요청"),
                    @ApiResponse(responseCode = "403", description = "인증 실패"),
                    @ApiResponse(responseCode = "500", description = "서버 오류")
            })
    ResponseEntity<CommentInfoResponse> getAllArticleComments(
            @Parameter(description = "게시글 아이디", required = true) Long articleId);

    @Operation(summary = "댓글 생성", description = "게시글에 댓글을 추가합니다.",
            responses = {
                    @ApiResponse(responseCode = "201", description = "댓글 생성 성공",
                    content = @Content(schema = @Schema(implementation = Long.class))),
                    @ApiResponse(responseCode = "400", description = "잘못된 요청"),
                    @ApiResponse(responseCode = "403", description = "인증 실패"),
                    @ApiResponse(responseCode = "500", description = "서버 오류")
            })
    ResponseEntity<Long> addComment(
            @Parameter(description = "토큰에서 추출한 사용자 이름", hidden = true) String username,
            @Parameter(description = "게시글 아이디", required = true) Long articleId,
            @Parameter(description = "댓글 내용", required = true) AddCommentRequest request);

    @Operation(summary = "댓글 업데이트", description = "댓글을 업데이트 합니다.",
            responses = {
                    @ApiResponse(responseCode = "204", description = "댓글 업데이트 성공"),
                    @ApiResponse(responseCode = "400", description = "잘못된 요청"),
                    @ApiResponse(responseCode = "403", description = "인증 실패"),
                    @ApiResponse(responseCode = "500", description = "서버 오류")
            })
    ResponseEntity<Void> updateComment(
            @Parameter(description = "토큰에서 추출한 사용자 이름", hidden = true) String username,
            @Parameter(description = "댓글 id", required = true) Long id,
            @Parameter(description = "댓글 내용", required = true) UpdateCommentRequest request);

    @Operation(summary = "댓글 삭제", description = "댓글을 삭제합니다.",
            responses = {
                    @ApiResponse(responseCode = "204", description = "북마크 삭제 성공"),
                    @ApiResponse(responseCode = "400", description = "잘못된 요청"),
                    @ApiResponse(responseCode = "403", description = "인증 실패"),
                    @ApiResponse(responseCode = "500", description = "서버 오류")
            })
    ResponseEntity<Void> deleteComment(
            @Parameter(description = "토큰에서 추출한 사용자 이름", hidden = true) String username,
            @Parameter(description = "댓글 id", required = true) Long id);
}