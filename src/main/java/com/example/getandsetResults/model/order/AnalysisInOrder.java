package com.example.getandsetResults.model.order;

import com.example.getandsetResults.entity.Analysis;
import com.example.getandsetResults.entity.OrderHasAnalysis;

import java.util.ArrayList;
import java.util.List;

public class AnalysisInOrder {
    private Long idAnalysis;
    private String name;
    private String surname;
    private Double result;

    public AnalysisInOrder(Long idAnalysis, String name, String surname, double result) {
        this.idAnalysis = idAnalysis;
        this.name = name;
        this.surname = surname;
        this.result = result;
    }

    public Long getIdAnalysis() {
        return idAnalysis;
    }

    public void setIdAnalysis(Long idAnalysis) {
        this.idAnalysis = idAnalysis;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public double getResult() {
        return result;
    }

    public void setResult(double result) {
        this.result = result;
    }

    public static List<AnalysisInOrder> convert(List<OrderHasAnalysis> orderHasAnalysis){
        List<AnalysisInOrder> result = new ArrayList<>();
       for (OrderHasAnalysis o: orderHasAnalysis){
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

