package com.example.getandsetResults.model.order;
import com.example.getandsetResults.entity.OrderHasAnalysis;

import java.sql.Timestamp;
import java.util.List;

public class OrderResponse {
    private Long idOrder;
    private Timestamp time;
    private List<AnalysisInOrder> analysisInOrderList;
    private String name;
    private String surname;

    public OrderResponse(Long idOrder, Timestamp time,
                         List<AnalysisInOrder> analysisInOrderList,
                         String surname_, String name_) {
        this.idOrder = idOrder;
        this.time = time;
        this.surname = surname_;
        this.name = name_;
        this.analysisInOrderList = analysisInOrderList;
    }

    public Long getIdOrder() {
        return idOrder;
    }

    public void setIdOrder(Long idOrder) {
        this.idOrder = idOrder;
    }

    public Timestamp getTime() {
        return time;
    }

    public void setTime(Timestamp time) {
        this.time = time;
    }

    public List<AnalysisInOrder> getAnalysisInOrderList() {
        return analysisInOrderList;
    }

    public void setAnalysisInOrderList(List<AnalysisInOrder> analysisInOrderList) {
        this.analysisInOrderList = analysisInOrderList;
    }

    public static OrderResponse convert(List<OrderHasAnalysis> orderHasAnalysis) {
      return new OrderResponse(
                    orderHasAnalysis.get(0).getOrder_().getIdOrder(),
                    orderHasAnalysis.get(0).getOrder_().getTime(),
                    AnalysisInOrder.convert(orderHasAnalysis),
                    orderHasAnalysis.get(0).getOrder_().getUser().getSurname(),
                    orderHasAnalysis.get(0).getOrder_().getUser().getName());
    }
}
