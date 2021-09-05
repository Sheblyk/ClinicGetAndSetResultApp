package com.example.getandsetResults.entity;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Embedded;
import java.io.Serializable;

@Embeddable
public class OrderHasAnalysisPK implements Serializable {
    @Column(name = "fk_order")
    private Long idOrder;

    @Column(name = "fk_analysis")
    private Long idAnalysis;


}
