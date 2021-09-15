package com.example.getandsetResults.repository;

import com.example.getandsetResults.entity.OrderHasAnalysis;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface OrderHasAnalysisRepository  extends JpaRepository<OrderHasAnalysis, Long> {
    @Query("select o from OrderHasAnalysis o where o.pk.idOrder = :idOrder")
    List<OrderHasAnalysis> find(Long idOrder);

    @Query("select o from OrderHasAnalysis o where o.order_.idOrder = :idOrder and o.analysis.idAnalysis = :idAnalysis ")
    OrderHasAnalysis findByAnalysis_IdAnalysisAAndOrder_idOrder(
            Long idAnalysis, Long idOrder
    );
}
