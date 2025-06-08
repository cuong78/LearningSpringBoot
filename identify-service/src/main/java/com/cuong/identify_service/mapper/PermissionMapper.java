package com.cuong.identify_service.mapper;

import com.cuong.identify_service.dto.request.PermissionRequest;
import com.cuong.identify_service.dto.response.PermissionResponse;
import com.cuong.identify_service.dto.response.UserResponse;
import com.cuong.identify_service.entity.Permission;
import com.cuong.identify_service.entity.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PermissionMapper {

    Permission toPerrmison(PermissionRequest permissionRequest);



    PermissionResponse toPerrmisonResponse(Permission permission);
}
