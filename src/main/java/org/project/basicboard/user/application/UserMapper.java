package org.project.basicboard.user.application;

import org.project.basicboard.user.api.dto.response.UserJoinResponse;
import org.project.basicboard.user.domain.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {
    public UserJoinResponse toUserJoinResponse(final User user) {
        return UserJoinResponse.from(user);
    }
}