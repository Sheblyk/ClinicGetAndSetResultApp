package com.example.getandsetResults.service.impl;

import com.example.getandsetResults.entity.OrderHasAnalysis;
import com.example.getandsetResults.model.order.OrderResponse;
import com.example.getandsetResults.repository.OrderHasAnalysisRepository;
import com.example.getandsetResults.service.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class OrderService implements IOrderService {

    private OrderHasAnalysisRepository orderRepo;

    @Autowired
    public OrderService(OrderHasAnalysisRepository orderRepo_){
        this.orderRepo = orderRepo_;
    }

    @Override
    public Optional<OrderResponse> find(Long idOrder) {
        List<OrderHasAnalysis> res = orderRepo.find(idOrder);
        return Optional.of(OrderResponse.convert(res));
    }
}
