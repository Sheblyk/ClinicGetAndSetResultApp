package com.example.getandsetResults.controller;

import com.example.getandsetResults.AppException;
import com.example.getandsetResults.model.order.OrderResponse;
import com.example.getandsetResults.service.IOrderService;
import com.example.getandsetResults.service.impl.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/orders")
public class OrderController {

    private IOrderService orderService;

    @Autowired
    public OrderController(IOrderService iOrderService_){
        this.orderService = iOrderService_;
    }

    @GetMapping("/{idOrder}")
    public OrderResponse getOrderResult(@RequestParam Long idOrder){
        OrderResponse orderResponse = orderService.find(idOrder);
        if(orderResponse==null){
           throw AppException.orderDoesNotExist(idOrder);
        }
        return orderResponse;
    }
}
