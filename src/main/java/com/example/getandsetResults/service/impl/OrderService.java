package com.example.getandsetResults.service.impl;

import com.example.getandsetResults.AppException;
import com.example.getandsetResults.entity.OrderHasAnalysis;
import com.example.getandsetResults.entity.Order_;
import com.example.getandsetResults.model.order.AnalysisInOrder;
import com.example.getandsetResults.model.order.AnalysisRequest;
import com.example.getandsetResults.model.order.OrderResponse;
import com.example.getandsetResults.repository.OrderHasAnalysisRepository;
import com.example.getandsetResults.repository.OrderRepository;
import com.example.getandsetResults.service.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@Transactional
public class OrderService implements IOrderService {

    private OrderHasAnalysisRepository orderHasAnalysisRepo;
    private OrderRepository orderRepo;

    @Autowired
    public OrderService(OrderHasAnalysisRepository orderHasAnalysisRepo_,
                        OrderRepository orderRepo_){
        this.orderHasAnalysisRepo = orderHasAnalysisRepo_;
        this.orderRepo = orderRepo_;
    }

    @Override
    public Optional<OrderResponse> find(Long idOrder) {
        List<OrderHasAnalysis> res = orderHasAnalysisRepo.find(idOrder);
        return Optional.of(OrderResponse.convert(res));
    }

    @Override
    public void update(AnalysisRequest request) {
        var order = this.find(request.idOrder())
                .orElseThrow(() -> AppException.orderDoesNotExist(request.idOrder()));

        int count = 0;
        for (AnalysisInOrder a : order.analysisInOrderList()) {
            if(Objects.equals(a.idAnalysis(), request.idAnalysis())){
                var orderHasAnalysis_ = orderHasAnalysisRepo.findByAnalysis_IdAnalysisAAndOrder_idOrder(
                        a.idAnalysis(), request.idOrder());
                orderHasAnalysis_.setResult(request.result());
                count++;
                break;
            }
        }

        if(ifOrderHasAllAnalysis(request.idOrder())){
            Order_ order_ = orderRepo.findByIdOrder(request.idOrder());
            order_.setFinished(true);
        }

        if(count == 0){
            throw AppException.analysisNotFount(request.idAnalysis());
        }
    }

    //return all orders from definite clinic
    //(usually admin`s workplace) and definite order state
    @Override
    public List<OrderResponse> findAllByClinicAndFinished(Long clinic, boolean finished) {

        List<Order_> orders = orderRepo.findAllByClinicAndFinished(clinic, finished);
        List<OrderResponse> orderResponses = new ArrayList<>();
        for (Order_ o: orders) {
            orderResponses.
                    add(OrderResponse.
                            convert(orderHasAnalysisRepo.find(o.getIdOrder())));
        }
        return orderResponses;
    }

    private boolean ifOrderHasAllAnalysis(Long idOrder){
        List<OrderHasAnalysis> orderHasAnalyses = orderHasAnalysisRepo.find(idOrder);

        int before = orderHasAnalyses.size();
        int count = orderHasAnalyses.size();
        for (OrderHasAnalysis o: orderHasAnalyses) {
            if(o.getResult() == 0.0){
                count--;
            }
        }

        return count == before;
    }
}
