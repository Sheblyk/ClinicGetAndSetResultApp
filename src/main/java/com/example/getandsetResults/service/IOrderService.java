package com.example.getandsetResults.service;

import com.example.getandsetResults.model.order.OrderResponse;

import java.util.Optional;

public interface IOrderService {
    Optional<OrderResponse> find(Long idOrder);
}
