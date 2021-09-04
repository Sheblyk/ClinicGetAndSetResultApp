package com.example.getandsetResults.repository;

import com.example.getandsetResults.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.UUID;

public interface RoleRepository extends JpaRepository<Role, UUID> {
   @Query("select r from Role r where r.roleName = :name")
    Role getByName(String name);
}
