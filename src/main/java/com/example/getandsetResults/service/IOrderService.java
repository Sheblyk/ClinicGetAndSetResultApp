package com.example.getandsetResults.service;

import com.example.getandsetResults.model.order.AnalysisRequest;
import com.example.getandsetResults.model.order.OrderResponse;

import java.util.List;
import java.util.Optional;

public interface IOrderService {
    Optional<OrderResponse> find(Long idOrder);

    List<OrderResponse> findAllByClinicAndFinished(Long clinic, boolean finished);

    public void update(AnalysisRequest request);
}
