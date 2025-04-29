package org.project.basicboard.auth.application;

import org.mapstruct.Mapper;
import org.project.basicboard.auth.application.dto.request.AccessTokenServiceRequest;
import org.project.basicboard.auth.application.dto.request.LoginServiceRequest;
import org.project.basicboard.auth.application.dto.request.UserInfoDto;
import org.project.basicboard.auth.application.dto.response.LoginServiceResponse;
import org.project.basicboard.auth.controller.dto.request.AccessTokenRequest;
import org.project.basicboard.auth.controller.dto.request.LoginRequest;
import org.project.basicboard.auth.controller.dto.response.LoginResponse;
import org.project.basicboard.global.jwt.api.dto.TokenDto;
import org.project.basicboard.user.domain.User;

@Mapper(componentModel = "spring")
public interface AuthMapper {
    LoginServiceRequest toLoginServiceRequest(LoginRequest loginRequest);

    UserInfoDto toUserInfoDto(User user);

    LoginServiceResponse toLoginServiceResponse(TokenDto tokenDto);

    LoginResponse toLoginResponse(LoginServiceResponse loginServiceResponse);

    AccessTokenServiceRequest toAccessTokenServiceRequest(AccessTokenRequest accessTokenRequest);


}