package com.cuong.identify_service.mapper;

import com.cuong.identify_service.dto.request.RoleRequest;
import com.cuong.identify_service.dto.response.RoleResponse;
import com.cuong.identify_service.entity.Role;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface RoleMapper {

    //because In Entity Role have RelationShip Many to many with permission
    @Mapping(target = "permissions", ignore = true)
    Role toRole(RoleRequest request);

    RoleResponse toRoleResponse(Role role);
}