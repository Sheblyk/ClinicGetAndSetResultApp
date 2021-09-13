package com.example.getandsetResults.model.order;

import com.example.getandsetResults.entity.Analysis;
import com.example.getandsetResults.entity.OrderHasAnalysis;
import com.example.getandsetResults.model.analysis.AnalysisResponse;
import com.example.getandsetResults.service.IAnalysisService;
import com.example.getandsetResults.service.impl.AnalysisService;

import java.util.ArrayList;
import java.util.List;

public record AnalysisInOrder(Long idAnalysis,
                              String name,
                              double result,
                              String description) {

    public static List<AnalysisInOrder> convert(List<OrderHasAnalysis> orderHasAnalysis) {
        List<AnalysisInOrder> result = new ArrayList<>();
        for (OrderHasAnalysis o : orderHasAnalysis) {
            Analysis analysis = o.getAnalysis();
            AnalysisInOrder currentResult = new AnalysisInOrder(analysis.getIdAnalysis(),
                    analysis.getName(),
                    o.getResult(),
                    analysis.getDescription());
            result.add(currentResult);
        }
        return result;
    }
}
