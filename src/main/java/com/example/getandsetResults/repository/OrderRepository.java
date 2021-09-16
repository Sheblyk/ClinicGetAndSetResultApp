package com.example.getandsetResults.repository;

import com.example.getandsetResults.entity.Order_;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order_, Long> {
    @Query("select o from Order_ o where o.clinic.idClinic = :clinic and o.finished = :finished")
    List<Order_> findAllByClinicAndFinished(Long clinic, boolean finished);

    Order_ findByIdOrder(Long idOrder);
}
