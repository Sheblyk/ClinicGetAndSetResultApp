package com.example.getandsetResults.repository;

import com.example.getandsetResults.entity.OrderHasAnalysis;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OrderHasAnalysisRepository  extends JpaRepository<OrderHasAnalysis, Long> {
    @Query("select o from OrderHasAnalysis o where o.pk.idOrder = :idOrder")
    List<OrderHasAnalysis> find(Long idOrder);
}
