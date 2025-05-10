package org.project.basicboard.user.application;

import org.mapstruct.Mapper;
import org.project.basicboard.user.application.dto.response.UserJoinServiceResponse;
import org.project.basicboard.user.domain.User;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserJoinServiceResponse toResponse(User user);
}