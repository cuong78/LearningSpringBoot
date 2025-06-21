package com.cuong.identify_service.repository;

import com.cuong.identify_service.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, String> {
    <T> Optional<T> findByName(String admin);
}
