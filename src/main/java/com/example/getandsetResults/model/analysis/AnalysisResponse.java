package com.example.getandsetResults.model.analysis;

import com.example.getandsetResults.entity.Analysis;

public record AnalysisResponse(Long idAnalysis,
                               String name,
                               String description,
                               Double price,
                               String categoryName) {

    public static AnalysisResponse convert(Analysis analysis){
        return new AnalysisResponse(analysis.getIdAnalysis(),
                analysis.getName(),
                analysis.getDescription(),
                analysis.getPrice(),
                analysis.getCategory().getCategoryName());
    }
}
