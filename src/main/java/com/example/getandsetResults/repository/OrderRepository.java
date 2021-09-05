package com.example.getandsetResults.repository;

import com.example.getandsetResults.entity.Order_;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order_, Long> {
}
