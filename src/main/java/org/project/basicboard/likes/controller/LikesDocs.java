package org.project.basicboard.likes.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;

@Tag(name = "[좋아요 API]",  description = "좋아요 관련 API")
interface LikesDocs {
    @Operation(summary = "좋아요 생성", description = "게시글에 좋아요를 추가합니다.",
            responses = {
                    @ApiResponse(responseCode = "204", description = "좋아요 생성 성공"),
                    @ApiResponse(responseCode = "400", description = "잘못된 요청"),
                    @ApiResponse(responseCode = "403", description = "인증 실패"),
                    @ApiResponse(responseCode = "500", description = "서버 오류")
            })
    ResponseEntity<Void> createLike(
            @Parameter(description = "토큰에서 추출한 사용자명", hidden = true) String username,
            @Parameter(description = "게시글 아이디", required = true) Long articleId);

    @Operation(summary = "좋아요 삭제", description = "게시글 좋아요를 삭제합니다.",
            responses = {
                    @ApiResponse(responseCode = "204", description = "좋아요 취소 성공"),
                    @ApiResponse(responseCode = "400", description = "잘못된 요청"),
                    @ApiResponse(responseCode = "403", description = "인증 실패"),
                    @ApiResponse(responseCode = "404", description = "좋아요를 찾을 수 없음."),
                    @ApiResponse(responseCode = "500", description = "서버 오류")
            })
    ResponseEntity<Void> deleteLike(
            @Parameter(description = "토큰에서 추출한 사용자명", hidden = true) String username,
            @Parameter(description = "게시글 아이디", required = true) Long articleId);
}