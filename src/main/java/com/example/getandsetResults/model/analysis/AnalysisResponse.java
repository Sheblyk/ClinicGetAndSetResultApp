package com.example.getandsetResults.model.analysis;

import com.example.getandsetResults.entity.Analysis;

public class AnalysisResponse {
    private Long idAnalysis;
    private String name;
    private String description;
    private Double price;
    private String categoryName;

    public AnalysisResponse(Long idAnalysis, String name, String description, Double price, String categoryName) {
        this.idAnalysis = idAnalysis;
        this.name = name;
        this.description = description;
        this.price = price;
        this.categoryName = categoryName;
    }

    public static AnalysisResponse convert(Analysis analysis){
        return new AnalysisResponse(analysis.getIdAnalysis(),
                analysis.getName(),
                analysis.getDescription(),
                analysis.getPrice(),
                analysis.getCategory().getCategoryName());
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }
}
