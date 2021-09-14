package com.example.getandsetResults.entity;

import lombok.*;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import javax.persistence.*;
import javax.validation.constraints.Null;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
public class OrderHasAnalysis {

    @EmbeddedId
    private OrderHasAnalysisPK pk;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("idOrder")
    @JoinColumn(name = "fk_order")
    private Order_ order_;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("idAnalysis")
    @JoinColumn(name = "fk_analysis")
    private Analysis analysis;

    private double result;
}
