package org.project.basicboard.auth.api;

import lombok.RequiredArgsConstructor;
import org.project.basicboard.auth.api.dto.request.CreateAccessTokenRequest;
import org.project.basicboard.auth.api.dto.request.LoginRequest;
import org.project.basicboard.auth.api.dto.request.NicknameUpdateRequest;
import org.project.basicboard.auth.api.dto.response.CreateAccessTokenResponse;
import org.project.basicboard.auth.application.TokenService;
import org.project.basicboard.auth.application.AuthService;
import org.project.basicboard.global.jwt.api.dto.TokenDto;
import org.project.basicboard.global.jwt.service.TokenProvider;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;
    private final TokenService tokenService;

    @PostMapping("/login")
    public ResponseEntity<TokenDto> login(@RequestBody @Validated LoginRequest request) {
        TokenDto response = authService.login(request);

        return ResponseEntity.ok(response);
    }

    @PostMapping("/token")
    public ResponseEntity<CreateAccessTokenResponse> createAccessToken(@RequestBody @Validated CreateAccessTokenRequest request) {
        CreateAccessTokenResponse response = tokenService.createAccessTokenForRefresh(request.refreshToken());

        return ResponseEntity.ok(response);
    }

    @PatchMapping("/nickname/{id}")
    public ResponseEntity<Void> updateNickname(@PathVariable Long id, @RequestBody @Validated NicknameUpdateRequest request) {
        authService.updateNickname(id, request.nickname());

        return ResponseEntity.noContent()
                .build();
    }
}