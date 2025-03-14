package com.cuong.identify_service.controller;

import com.cuong.identify_service.dto.request.UserCreationRequest;
import com.cuong.identify_service.dto.request.UserUpdateRequest;
import com.cuong.identify_service.dto.response.ApiResponse;
import com.cuong.identify_service.entity.User;
import com.cuong.identify_service.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping
    ApiResponse<User> createUser(@RequestBody @Valid UserCreationRequest request) {
      ApiResponse<User> apiResponse =new ApiResponse<>();
      apiResponse.setResult(userService.createUser(request));
    return apiResponse;
    }


    @GetMapping
    List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/{userId}")
    User getUserById( @PathVariable String userId) {
        return userService.getUserById(userId);
    }

    @PutMapping("/{userId}")
    User updateUser(@PathVariable String userId, @RequestBody UserUpdateRequest request) {
        return userService.updateUser(userId, request);
    }

    @DeleteMapping("/{userId}")
    String deleteUser(@PathVariable String userId) {
        userService.deleteUser(userId);
        return "User deleted";
    }


}
