package com.example.getandsetResults.model.order;

import com.example.getandsetResults.entity.Analysis;
import com.example.getandsetResults.entity.OrderHasAnalysis;

import java.util.ArrayList;
import java.util.List;

public record AnalysisInOrder(Long idAnalysis,
                              String name,
                              String surname,
                              double result) {

    public static List<AnalysisInOrder> convert(List<OrderHasAnalysis> orderHasAnalysis) {
        List<AnalysisInOrder> result = new ArrayList<>();
        for (OrderHasAnalysis o : orderHasAnalysis) {
            Analysis analysis = o.getAnalysis();
            AnalysisInOrder currentResult = new AnalysisInOrder(analysis.getIdAnalysis(),
                    analysis.getName(),
                    analysis.getDescription(),
                    o.getResult());
            result.add(currentResult);
        }
        return result;
    }
}
