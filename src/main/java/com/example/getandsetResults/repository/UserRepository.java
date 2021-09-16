package com.example.getandsetResults.repository;

import com.example.getandsetResults.entity.Analysis;
import com.example.getandsetResults.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findById(Long id);
}

