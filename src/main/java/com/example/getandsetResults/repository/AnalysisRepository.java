package com.example.getandsetResults.repository;

import com.example.getandsetResults.entity.Analysis;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface AnalysisRepository extends JpaRepository<Analysis, UUID> {
}
