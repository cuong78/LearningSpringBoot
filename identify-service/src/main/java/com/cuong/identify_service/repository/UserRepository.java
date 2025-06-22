package com.cuong.identify_service.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cuong.identify_service.entity.User;

public interface UserRepository extends JpaRepository<User, String> {
    // kiểm tra tồn tại
    boolean existsByUsername(String username);

    Optional<User> findByUsername(String username);
}
