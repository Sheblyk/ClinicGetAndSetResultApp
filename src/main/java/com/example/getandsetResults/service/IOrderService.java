package com.example.getandsetResults.service;

import com.example.getandsetResults.model.order.OrderResponse;

public interface IOrderService {
    OrderResponse find(Long idOrder);
}
