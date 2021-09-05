package com.example.getandsetResults.service;

import com.example.getandsetResults.model.analysis.AnalysisResponse;

import java.util.List;
import java.util.Optional;

public interface IAnalysisService {
    List<AnalysisResponse> getAllAnalysis();

    List<AnalysisResponse> getAnalysisByCategory(Long id);

    Optional<AnalysisResponse> getById(Long id);
}

