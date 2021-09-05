package com.example.getandsetResults.entity;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import javax.persistence.*;
import javax.validation.constraints.Null;

@Entity
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

    public OrderHasAnalysis() {
    }

    public OrderHasAnalysis(Order_ order_, Analysis analysis, double result) {
        this.order_ = order_;
        this.analysis = analysis;
        this.result = result;
    }

    public Order_ getOrder_() {
        return order_;
    }

    public void setOrder_(Order_ order_) {
        this.order_ = order_;
    }

    public Analysis getAnalysis() {
        return analysis;
    }

    public void setAnalysis(Analysis analysis) {
        this.analysis = analysis;
    }

    public double getResult() {
        return result;
    }

    public void setResult(double result) {
        this.result = result;
    }

    public OrderHasAnalysisPK getPk() {
        return pk;
    }

    public void setPk(OrderHasAnalysisPK pk) {
        this.pk = pk;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        OrderHasAnalysis that = (OrderHasAnalysis) o;

        return new EqualsBuilder()
                .append(result, that.result)
                .append(order_, that.order_)
                .append(analysis, that.analysis)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(order_)
                .append(analysis)
                .append(result)
                .toHashCode();
    }
}
