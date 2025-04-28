package org.project.basicboard.user.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.project.basicboard.global.annotation.AuthUsername;
import org.project.basicboard.user.application.UserMapper;
import org.project.basicboard.user.application.dto.response.UserJoinServiceResponse;
import org.project.basicboard.user.controller.dto.request.NicknameUpdateRequest;
import org.project.basicboard.user.controller.dto.request.UserJoinRequest;
import org.project.basicboard.user.controller.dto.response.UserJoinResponse;
import org.project.basicboard.user.application.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
public class UserApiController {

    private final UserService userService;
    private final UserMapper mapper;

    @PostMapping("/join")
    public ResponseEntity<UserJoinResponse> join(@RequestBody @Valid UserJoinRequest request) {
        UserJoinServiceResponse dto = userService.joinProcess(mapper.toUserJoinServiceRequest(request));

        UserJoinResponse response = mapper.toUserJoinResponse(dto);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(response);
    }

    @PatchMapping("/nickname")
    public ResponseEntity<Void> updateNickname(@AuthUsername String username,
                                               @RequestBody @Valid NicknameUpdateRequest request) {
        userService.updateNickname(username, request.nickname());

        return ResponseEntity.noContent()
                .build();
    }
}