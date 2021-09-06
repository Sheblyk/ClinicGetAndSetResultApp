package com.example.getandsetResults.controller;

import com.example.getandsetResults.AppException;
import com.example.getandsetResults.model.order.OrderResponse;
import com.example.getandsetResults.service.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/orders")
public class OrderController {

    private IOrderService orderService;

    @Autowired
    public OrderController(IOrderService iOrderService_){
        this.orderService = iOrderService_;
    }

    @GetMapping("/myOrders")
    public OrderResponse getOrderResult(@RequestParam Long idOrder){
        return orderService.find(idOrder)
                .orElseThrow(() -> AppException.orderDoesNotExist(idOrder));
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping("/order")
    public void update(@RequestParam Long idOrder, @RequestParam Long idAnalysis){

    }
}
