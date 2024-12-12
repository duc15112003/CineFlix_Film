package com.cineflix.vn.service.modelService.service;

import com.cineflix.vn.model.User;
import com.cineflix.vn.model.dto.request.UserRequest;
import com.cineflix.vn.model.dto.response.UserResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {
    List<UserResponse> findAllUsers();
    UserResponse createUser(UserRequest userRequest);
    UserResponse updateUser(UserRequest userRequest);
    Boolean deleteUser(String username);
    User findByUsername(String username);
}
