package org.project.basicboard.user.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.project.basicboard.user.controller.dto.request.NicknameUpdateRequest;
import org.project.basicboard.user.controller.dto.request.UserJoinRequest;
import org.project.basicboard.user.controller.dto.response.UserJoinResponse;
import org.springframework.http.ResponseEntity;

@Tag(name = "[사용자 API]", description = "사용자 관련 API")
public interface UserDocs {

    @Operation(summary = "사용자 회원가입", description = "사용자 정보를 입력받아 회원가입을 진행합니다.",
            responses = {
                    @ApiResponse(responseCode = "201", description = "회원가입에 성공",
                            content = @Content(schema = @Schema(implementation = UserJoinResponse.class))),
                    @ApiResponse(responseCode = "400", description = "잘못된 요청"),
                    @ApiResponse(responseCode = "403", description = "인증 실패"),
                    @ApiResponse(responseCode = "500", description = "서버 오류")
            })
    ResponseEntity<UserJoinResponse> join(
            @Parameter(description = "회원가입하는 사용자 정보", required = true) UserJoinRequest request);

    @Operation(summary = "사용자 닉네임 업데이트", description = "닉네임을 업데이트합니다.",
            responses = {
                    @ApiResponse(responseCode = "204", description = "닉네임 업데이트에 성공",
                            content = @Content(schema = @Schema(implementation = UserJoinResponse.class))),
                    @ApiResponse(responseCode = "400", description = "잘못된 요청"),
                    @ApiResponse(responseCode = "403", description = "인증 실패"),
                    @ApiResponse(responseCode = "500", description = "서버 오류")
            })
    ResponseEntity<Void> updateNickname(
            @Parameter(description = "토큰에서 추출한 사용자 이름", hidden = true) String username,
            @Parameter(description = "바꿀 닉네임", required = true) NicknameUpdateRequest request);
}
