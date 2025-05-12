package org.project.basicboard.auth.controller.dto.request;

import jakarta.validation.constraints.NotBlank;
import org.project.basicboard.auth.application.dto.request.LoginServiceRequest;

public record LoginRequest(
        @NotBlank(message = "아이디가 비어있습니다.") String username,
        @NotBlank(message = "비밀번호가 비어있습니다.") String password
) {
    public LoginServiceRequest toServiceRequest(){
        return LoginServiceRequest.builder()
                .username(username)
                .password(password)
                .build();
    }
}