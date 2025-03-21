package com.cuong.identify_service.controller;

import com.cuong.identify_service.dto.request.UserCreationRequest;
import com.cuong.identify_service.dto.request.UserUpdateRequest;
import com.cuong.identify_service.dto.response.ApiResponse;
import com.cuong.identify_service.dto.response.UserResponse;
import com.cuong.identify_service.entity.User;
import com.cuong.identify_service.service.UserService;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserController {


     UserService userService;

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
    UserResponse getUserById(@PathVariable String userId) {
        return userService.getUserById(userId);
    }

    @PutMapping("/{userId}")
    UserResponse updateUser(@PathVariable String userId, @RequestBody UserUpdateRequest request) {
        return userService.updateUser(userId, request);
    }

    @DeleteMapping("/{userId}")
    String deleteUser(@PathVariable String userId) {
        userService.deleteUser(userId);
        return "User deleted";
    }


}
