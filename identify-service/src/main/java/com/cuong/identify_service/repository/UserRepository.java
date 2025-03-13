package com.cuong.identify_service.repository;

import com.cuong.identify_service.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, String> {

}
