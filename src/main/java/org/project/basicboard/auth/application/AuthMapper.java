package org.project.basicboard.auth.application;

import org.mapstruct.Mapper;

import org.project.basicboard.auth.application.dto.request.UserInfoRequest;
import org.project.basicboard.auth.application.dto.response.LoginServiceResponse;
import org.project.basicboard.global.jwt.api.dto.TokenResponse;
import org.project.basicboard.user.domain.User;

@Mapper(componentModel = "spring")
public interface AuthMapper {
    UserInfoRequest toUserInfoRequest(User user);

    LoginServiceResponse toLoginServiceResponse(TokenResponse tokenResponse);
}