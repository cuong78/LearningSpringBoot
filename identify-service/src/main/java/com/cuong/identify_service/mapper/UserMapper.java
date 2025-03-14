package com.cuong.identify_service.mapper;

import com.cuong.identify_service.dto.request.UserCreationRequest;
import com.cuong.identify_service.dto.request.UserUpdateRequest;
import com.cuong.identify_service.dto.response.UserResponse;
import com.cuong.identify_service.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface UserMapper {

    // map userCreatetionRequest vào user
    User toUser(UserCreationRequest userCreationRequest);

    // map User Update vào user
    void updateUser(@MappingTarget User user, UserUpdateRequest userUpdateRequest);

    // chỉ định cột lastName của UserResponse sẽ đc map với giá trị firstName của user
   //  @Mapping(source = "firstName",  target = "lastName")

    // chỉ định cột target của UserRespone sẽ không hiện thị
   // @Mapping(target = "lastName", ignore = true)
    UserResponse toUserResponse(User user);

}
