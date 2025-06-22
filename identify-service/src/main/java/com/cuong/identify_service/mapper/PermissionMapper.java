package com.cuong.identify_service.mapper;

import org.mapstruct.Mapper;

import com.cuong.identify_service.dto.request.PermissionRequest;
import com.cuong.identify_service.dto.response.PermissionResponse;
import com.cuong.identify_service.entity.Permission;

@Mapper(componentModel = "spring")
public interface PermissionMapper {

    Permission toPerrmison(PermissionRequest permissionRequest);

    PermissionResponse toPerrmisonResponse(Permission permission);
}
