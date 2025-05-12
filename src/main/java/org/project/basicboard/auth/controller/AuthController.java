package org.project.basicboard.auth.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.project.basicboard.auth.application.AuthService;
import org.project.basicboard.auth.application.TokenService;
import org.project.basicboard.auth.application.dto.response.AccessTokenServiceResponse;
import org.project.basicboard.auth.application.dto.response.LoginServiceResponse;
import org.project.basicboard.auth.controller.dto.request.AccessTokenRequest;
import org.project.basicboard.auth.controller.dto.request.LoginRequest;
import org.project.basicboard.auth.controller.dto.response.AccessTokenResponse;
import org.project.basicboard.auth.controller.dto.response.LoginResponse;
import org.project.basicboard.global.annotation.AuthUsername;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class AuthController implements AuthDocs {

    private final AuthService authService;
    private final TokenService tokenService;

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(
            @RequestBody @Valid LoginRequest request) {
        LoginServiceResponse result = authService.login(request.toServiceRequest());

        LoginResponse response = LoginResponse.from(result);

        return ResponseEntity.ok(response);
    }

    @PostMapping("/token")
    public ResponseEntity<AccessTokenResponse> createAccessToken(
            @RequestBody @Valid AccessTokenRequest request) {
        AccessTokenServiceResponse result = tokenService.createAccessTokenForRefresh(request.toServiceRequest());

        AccessTokenResponse response = AccessTokenResponse.from(result);

        return ResponseEntity.ok(response);
    }

    @PostMapping("/logout")
    public ResponseEntity<Void> logout(
            @AuthUsername String username) {
        authService.logout(username);

        return ResponseEntity.noContent()
                .build();
    }
}