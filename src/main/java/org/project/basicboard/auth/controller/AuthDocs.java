package org.project.basicboard.auth.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.project.basicboard.auth.controller.dto.request.AccessTokenRequest;
import org.project.basicboard.auth.controller.dto.request.LoginRequest;
import org.project.basicboard.auth.controller.dto.response.AccessTokenResponse;
import org.project.basicboard.auth.controller.dto.response.LoginResponse;
import org.springframework.http.ResponseEntity;

@Tag(name = "[인증 API]", description = "인증 관련 API")
public interface AuthDocs {
    @Operation(summary = "사용자 로그인(토큰 x)", description = "아이디와 비밀번호로 로그인합니다.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "로그인에 성공합니다.",
                            content = @Content(schema = @Schema(implementation = LoginResponse.class))),
                    @ApiResponse(responseCode = "400", description = "잘못된 요청"),
                    @ApiResponse(responseCode = "403", description = "인증 실패"),
                    @ApiResponse(responseCode = "404", description = "사용자를 찾을 수 없음."),
                    @ApiResponse(responseCode = "500", description = "서버 오류")
            })
    ResponseEntity<LoginResponse> login(@Parameter(description = "사용자 아이디와 비밀번호", required = true) LoginRequest request);

    @Operation(summary = "액세스 토큰 재발급(토큰 x)", description = "리프레시 토큰을 통해 액세스 토큰을 재발급합니다.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "재발급 성공",
                            content = @Content(schema = @Schema(implementation = AccessTokenResponse.class))),
                    @ApiResponse(responseCode = "400", description = "잘못된 요청"),
                    @ApiResponse(responseCode = "403", description = "인증 실패"),
                    @ApiResponse(responseCode = "404", description = "토큰과 일치하는 사용자를 찾을 수 없음."),
                    @ApiResponse(responseCode = "500", description = "서버 오류")
            })
    ResponseEntity<AccessTokenResponse> createAccessToken(@Parameter(description = "리프레시 토큰", required = true) AccessTokenRequest request);

    @Operation(summary = "사용자 로그아웃", description = "로그아웃을 진행합니다.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "로그인에 성공합니다."),
                    @ApiResponse(responseCode = "400", description = "잘못된 요청"),
                    @ApiResponse(responseCode = "403", description = "인증 실패"),
                    @ApiResponse(responseCode = "404", description = "로그아웃할 사용자를 찾을 수 없음."),
                    @ApiResponse(responseCode = "500", description = "서버 오류")
            })
    ResponseEntity<Void> logout(String username);
}
