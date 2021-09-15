package com.example.getandsetResults.repository;

import com.example.getandsetResults.entity.Analysis;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface AnalysisRepository extends JpaRepository<Analysis, Long> {

    @Query("select a from Analysis a where a.category.idCategory = :idCategory")
    List<Analysis> getAnalysesByIdCategory(Long idCategory);

    List<Analysis> findAll();

    Optional<Analysis> findById(Long id);
}
