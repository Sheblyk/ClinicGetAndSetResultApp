package com.example.getandsetResults.service.impl;

import com.example.getandsetResults.AppException;
import com.example.getandsetResults.entity.Category;
import com.example.getandsetResults.model.analysis.AnalysisResponse;
import com.example.getandsetResults.repository.AnalysisRepository;
import com.example.getandsetResults.repository.CategoryRepository;
import com.example.getandsetResults.service.IAnalysisService;
import com.example.getandsetResults.service.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AnalysisService implements IAnalysisService, ICategoryService {

    private final AnalysisRepository analysisRepo;
    private final CategoryRepository categoryRepo;

    @Autowired
    public AnalysisService(AnalysisRepository analysisRepo_,
                           CategoryRepository categoryRepo_) {
        this.analysisRepo = analysisRepo_;
        this.categoryRepo = categoryRepo_;
    }

    @Override
    @Transactional(readOnly = true)
    public List<AnalysisResponse> getAllAnalysis() {
        return analysisRepo.findAll().
                stream().
                map(AnalysisResponse::convert).
                collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<AnalysisResponse> getById(Long id) {
        return analysisRepo.findById(id).
                map(AnalysisResponse::convert);
    }

    @Override
    @Transactional(readOnly = true)
    public List<AnalysisResponse> getAnalysisByCategory(Long id) {
     var category = categoryRepo.findById(id).orElseThrow(()-> AppException.categoryDoesNotExist(id));
        return analysisRepo.getAnalysesByIdCategory(category.getIdCategory()).
                stream().
                map(AnalysisResponse::convert).
                collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<Category> getAllCategories() {
        return categoryRepo.findAll();
    }
}
