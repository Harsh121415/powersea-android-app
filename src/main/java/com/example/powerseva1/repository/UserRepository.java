package com.example.powerseva1.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.powerseva1.entity.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);

}