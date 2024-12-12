package com.cineflix.vn.model.mapper;

import com.cineflix.vn.model.User;
import com.cineflix.vn.model.dto.request.UserRequest;
import com.cineflix.vn.model.dto.response.UserResponse;
import org.mapstruct.*;
import org.mapstruct.factory.*;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);
    UserResponse toUserResponse(User user);
    List<UserResponse> toListUserResponse(List<User> list);
    User TranferToUserRequestfromUser(UserRequest userRequest) ;
    UserRequest TranferToUserRequestfromUser(User user);
}
