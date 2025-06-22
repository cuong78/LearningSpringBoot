package com.cuong.identify_service.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cuong.identify_service.entity.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, String> {
    <T> Optional<T> findByName(String admin);
}
