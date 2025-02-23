package com.weatherapp.dashboard.repository;

import com.weatherapp.dashboard.dto.UserRequest;
import com.weatherapp.dashboard.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
   // User register(UserRequest userRequest);
    Optional<User> findByUsername(String username);
}
