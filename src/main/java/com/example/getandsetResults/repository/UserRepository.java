package com.example.getandsetResults.repository;

import com.example.getandsetResults.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UserRepository extends JpaRepository<User, Long> {

}

