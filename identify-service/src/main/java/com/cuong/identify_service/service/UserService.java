package com.cuong.identify_service.service;

import com.cuong.identify_service.dto.request.UserCreationRequest;
import com.cuong.identify_service.dto.request.UserUpdateRequest;
import com.cuong.identify_service.dto.response.UserResponse;
import com.cuong.identify_service.entity.User;
import com.cuong.identify_service.exception.AppException;
import com.cuong.identify_service.exception.ErrorCode;
import com.cuong.identify_service.mapper.UserMapper;
import com.cuong.identify_service.repository.UserRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserService {

    UserRepository userRepository;

    UserMapper userMapper;


    public User createUser(UserCreationRequest request) {

        if(userRepository.existsByUsername(request.getUsername())) {
            throw new AppException(ErrorCode.USER_EXSITTED );
        }


        User user = userMapper.toUser(request);

        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(10);
        user.setPassword(passwordEncoder.encode(user.getPassword()));

            return userRepository.save(user);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public UserResponse getUserById(String id) {
     return userMapper.toUserResponse(userRepository.findById(id).orElseThrow(() ->new RuntimeException("User not found")));
    }

    public UserResponse updateUser(String userId,UserUpdateRequest request) {
        User user = userRepository.findById(userId).orElseThrow(() ->new RuntimeException("User not found"));
        userMapper.updateUser(user,request);
        return userMapper.toUserResponse(userRepository.save(user));
    }
    public void deleteUser(String userId) {
        userRepository.deleteById(userId);
    }

}
