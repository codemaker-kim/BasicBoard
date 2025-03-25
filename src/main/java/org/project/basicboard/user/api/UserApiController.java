package org.project.basicboard.user.api;

import lombok.RequiredArgsConstructor;
import org.project.basicboard.user.api.dto.request.UserJoinRequest;
import org.project.basicboard.user.api.dto.response.UserJoinResponse;
import org.project.basicboard.user.application.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserApiController {

    private final UserService userService;

    @PostMapping("/join")
    public ResponseEntity<UserJoinResponse> join(@RequestBody @Validated UserJoinRequest request) {
        UserJoinResponse response = userService.joinProcess(request);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(response);
    }
}
