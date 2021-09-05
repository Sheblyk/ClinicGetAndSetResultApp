package com.example.getandsetResults.repository;

import com.example.getandsetResults.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface RoleRepository extends JpaRepository<Role, Long> {
   @Query("select r from Role r where r.roleName = :name")
    Role getByName(String name);
}
