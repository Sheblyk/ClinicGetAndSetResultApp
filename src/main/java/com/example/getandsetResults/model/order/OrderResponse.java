package com.example.getandsetResults.model.order;

import com.example.getandsetResults.entity.OrderHasAnalysis;

import java.sql.Timestamp;
import java.util.List;

public record OrderResponse(Long idOrder,
                            Timestamp time,
                            List<AnalysisInOrder>analysisInOrderList,
                            String surname_,
                            String name_) {

    public static OrderResponse convert(List<OrderHasAnalysis> orderHasAnalysis) {
        return new OrderResponse(
                orderHasAnalysis.get(0).getOrder_().getIdOrder(),
                orderHasAnalysis.get(0).getOrder_().getTime(),
                AnalysisInOrder.convert(orderHasAnalysis),
                orderHasAnalysis.get(0).getOrder_().getUser().getSurname(),
                orderHasAnalysis.get(0).getOrder_().getUser().getName());
    }
}
