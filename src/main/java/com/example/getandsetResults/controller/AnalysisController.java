package com.example.getandsetResults.controller;

import com.example.getandsetResults.AppException;
import com.example.getandsetResults.model.analysis.AnalysisResponse;
import com.example.getandsetResults.service.IAnalysisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/service")
public class AnalysisController {

    private final IAnalysisService analysisService;

    @Autowired
    public AnalysisController(IAnalysisService analysisService_) {
        this.analysisService = analysisService_;
    }

    @GetMapping("/analysis")
    ResponseEntity<List<AnalysisResponse>> getAllAnalysis() {
        List<AnalysisResponse> analysis = analysisService.getAllAnalysis();
        return analysis != null && !analysis.isEmpty()
                ? new ResponseEntity<>(analysis, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/analysis/{id}")
    AnalysisResponse getById(@RequestParam Long idAnalysis) {
        return analysisService.getById(idAnalysis).orElseThrow(() ->
                AppException.analysisNotFount(idAnalysis));
    }

    @GetMapping("/categories/{id}")
    ResponseEntity<List<AnalysisResponse>> getAllCategories(@RequestParam Long idCategory) {
        List<AnalysisResponse> analysis = analysisService.getAnalysisByCategory(idCategory);
        return analysis != null && !analysis.isEmpty()
                ? new ResponseEntity<>(analysis, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
