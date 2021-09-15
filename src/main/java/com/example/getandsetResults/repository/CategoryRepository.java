package com.example.getandsetResults.repository;

import com.example.getandsetResults.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {

}
