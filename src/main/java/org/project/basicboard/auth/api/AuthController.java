package org.project.basicboard.auth.api;

import lombok.RequiredArgsConstructor;
import org.project.basicboard.auth.api.dto.request.LoginRequest;
import org.project.basicboard.user.api.dto.request.UserJoinRequest;
import org.project.basicboard.user.api.dto.response.UserJoinResponse;
import org.project.basicboard.auth.application.AuthService;
import org.project.basicboard.global.jwt.api.dto.TokenDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<TokenDto> login(@RequestBody @Validated LoginRequest request) {
        TokenDto response = authService.login(request);

        return ResponseEntity.ok(response);
    }
}