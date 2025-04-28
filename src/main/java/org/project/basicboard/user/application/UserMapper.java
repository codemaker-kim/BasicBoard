package org.project.basicboard.user.application;

import org.mapstruct.Mapper;
import org.project.basicboard.user.application.dto.request.UserJoinServiceRequest;
import org.project.basicboard.user.application.dto.response.UserJoinServiceResponse;
import org.project.basicboard.user.controller.dto.request.UserJoinRequest;
import org.project.basicboard.user.controller.dto.response.UserJoinResponse;
import org.project.basicboard.user.domain.User;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserJoinServiceResponse toResponse(User user);

    UserJoinServiceRequest toUserJoinServiceRequest(UserJoinRequest userJoinRequest);

    UserJoinResponse toUserJoinResponse(UserJoinServiceResponse userJoinServiceResponse);
}