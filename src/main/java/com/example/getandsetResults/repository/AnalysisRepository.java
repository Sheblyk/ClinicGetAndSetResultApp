package com.example.getandsetResults.repository;

import com.example.getandsetResults.entity.Analysis;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AnalysisRepository extends JpaRepository<Analysis, Long> {

    List<Analysis> getAnalysesByCategory(Long idCategory);

    List<Analysis> findAll();

    Optional<Analysis> findById(Long id);
}
