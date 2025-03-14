package com.cuong.identify_service.repository;

import com.cuong.identify_service.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, String> {
    //kiểm tra tồn tại
    boolean existsByUsername(String username);
    Optional<User> findByUsername(String username);

}
