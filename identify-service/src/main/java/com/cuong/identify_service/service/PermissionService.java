package com.cuong.identify_service.service;

import com.cuong.identify_service.dto.request.PermissionRequest;
import com.cuong.identify_service.dto.response.PermissionResponse;
import com.cuong.identify_service.entity.Permission;
import com.cuong.identify_service.mapper.PermissionMapper;
import com.cuong.identify_service.repository.PermissionRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class PermissionService {
    PermissionRepository permissionRepository;
    PermissionMapper permissionMapper;

    public PermissionResponse create (PermissionRequest request){
        Permission permission = permissionMapper.toPerrmison(request);
        permission = permissionRepository.save(permission);
        return permissionMapper.toPerrmisonResponse(permission);
    }

    public List<PermissionResponse> getAll(){
        var permissions = permissionRepository.findAll();
      return  permissions.stream().map(permissionMapper::toPerrmisonResponse).toList();
    }

    public  void delete (String permission){
        permissionRepository.deleteById(permission);
    }
}
