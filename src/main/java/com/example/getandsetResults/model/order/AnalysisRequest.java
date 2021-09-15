package com.example.getandsetResults.model.order;

import javax.validation.constraints.NotNull;

public record AnalysisRequest(Long idOrder,
                              Long idAnalysis,
                              @NotNull double result) {
}
