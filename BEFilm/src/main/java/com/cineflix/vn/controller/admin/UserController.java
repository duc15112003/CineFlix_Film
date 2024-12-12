package com.cineflix.vn.controller.admin;

import com.cineflix.vn.model.dto.request.UserRequest;
import com.cineflix.vn.model.dto.response.UserResponse;
import com.cineflix.vn.service.modelService.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/admin/user")
public class UserController {

    @Autowired
    UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<UserResponse>> getAllUsers() {
        List<UserResponse> users = userService.findAllUsers();
        return ResponseEntity.ok(users);
    }

    @PostMapping("/create")
    public ResponseEntity<UserResponse> CreateUser(@RequestBody UserRequest userRequest) {
        UserResponse userResponse = userService.createUser(userRequest);
        return ResponseEntity.ok(userResponse);
    }

    @PutMapping
    public ResponseEntity<UserResponse> UpdateUser(@RequestBody UserRequest userRequest) {
        UserResponse userResponse = userService.updateUser(userRequest);
        return ResponseEntity.ok(userResponse);
    }

    @DeleteMapping
    public ResponseEntity<?> DeleteUser(@RequestParam String username) {
        Boolean ret = userService.deleteUser(username);
        if(ret == true){
            return ResponseEntity.ok("Xoa thanh cong");
        }else {
            return (ResponseEntity<?>) ResponseEntity.badRequest();
        }
    }

}
