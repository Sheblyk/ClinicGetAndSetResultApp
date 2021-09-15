package com.example.getandsetResults.service.impl;

import com.example.getandsetResults.entity.Analysis;
import com.example.getandsetResults.entity.Category;
import com.example.getandsetResults.model.analysis.AnalysisResponse;
import com.example.getandsetResults.repository.AnalysisRepository;
import com.example.getandsetResults.repository.CategoryRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class AnalysisServiceTest {

    private AnalysisRepository analysisRepo;
    private CategoryRepository categoryRepo;
    private AnalysisService analysisService;

    @BeforeEach
    void setUp() {
        analysisRepo = mock(AnalysisRepository.class);
        categoryRepo = mock(CategoryRepository.class);
        analysisService = new AnalysisService(analysisRepo, categoryRepo);
    }

    @Test
    void getAllAnalysis() {
        Category categoryNotEmpty = new Category(1L, "CategoryName", new ArrayList<>());
        Analysis analysis = new Analysis(1L, "testName", "testDescription", 23.1, categoryNotEmpty );
        Analysis analysis2 = new Analysis(2L, "testNam2", "testDescriptio2", 23.1, categoryNotEmpty );

        List<Analysis> analyses = new ArrayList<>();
        analyses.add(analysis);
        analyses.add(analysis2);
        categoryNotEmpty.setAnalyses(analyses);

        List<AnalysisResponse> analysisResponses = new ArrayList<>();
        for (Analysis a: analyses) {
            analysisResponses.add(AnalysisResponse.convert(a));
        }

        when(analysisRepo.findAll()).thenReturn(analyses);
        List<AnalysisResponse> analysisResponseGETTER = analysisService.getAllAnalysis();

        assertEquals(2, analysisResponseGETTER.size());
        verify(analysisRepo, times(1)).findAll();
    }

    @Test
    void getById() {
        Long presentId = 1L;
        Long absentId = 2L;
        Category category = new Category(1L, "CategoryName", new ArrayList<>());
        var analysis = new Analysis(presentId, "testName", "testDescription", 23.1, category);

        when(analysisRepo.findById(absentId)).thenReturn(Optional.empty());
        when(analysisRepo.findById(presentId)).thenReturn(Optional.of(analysis));

        Optional<AnalysisResponse> absentResponse = analysisService.getById(absentId);

        assertThat(absentResponse).isEmpty();
        verify(analysisRepo).findById(absentId);

        Optional<AnalysisResponse> presentResponse = analysisService.getById(presentId);

        assertThat(presentResponse).hasValueSatisfying(response ->
                assertAnalysisMatchesResponse(analysis, response));

        verify(analysisRepo).findById(presentId);
        verifyNoMoreInteractions(analysisRepo);
    }

    @Test
    void getAllCategories() {
        Category category1 = new Category(1L, "CategoryName1", new ArrayList<>());
        Category category2 = new Category(2L, "CategoryName2", new ArrayList<>());
        Category category3 = new Category(3L, "CategoryName3", new ArrayList<>());

        List<Category> categories = new ArrayList<>();
        categories.add(category1);
        categories.add(category2);
        categories.add(category3);

        when(categoryRepo.findAll()).thenReturn(categories);

        List<Category> categoriesGETTED = analysisService.getAllCategories();
        assertEquals(3, categoriesGETTED.size());
        verify(categoryRepo, times(1)).findAll();
    }

    private static void assertAnalysisMatchesResponse(Analysis analysis,
                                                     AnalysisResponse analysisResponse) {
        assertThat(analysisResponse.idAnalysis()).isEqualTo(analysis.getIdAnalysis());
        assertThat(analysisResponse.name()).isEqualTo(analysis.getName());
        assertThat(analysisResponse.description()).isEqualTo(analysis.getDescription());
        assertThat(analysisResponse.price()).isEqualTo(analysis.getPrice());
        assertThat(analysisResponse.categoryName()).isEqualTo(analysis.getCategory().getCategoryName());
    }
}