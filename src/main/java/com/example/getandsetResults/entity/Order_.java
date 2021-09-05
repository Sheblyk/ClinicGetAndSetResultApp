package com.example.getandsetResults.entity;

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Order_ {
    @Id
    @GeneratedValue
    private Long idOrder;

    @Column(nullable = false)
    private final Timestamp time = Timestamp.valueOf(LocalDateTime.now());

    @Column(nullable = false)
    private Double priceForOrder;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "order_")
    private List<OrderHasAnalysis> analysis = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="idUser", nullable=false)
    private User user;

    public Order_(){}

    public Long getIdOrder() {
        return idOrder;
    }

    public void setIdOrder(Long idOrder) {
        this.idOrder = idOrder;
    }

    public Timestamp getTime() {
        return time;
    }

    public List<OrderHasAnalysis> getAnalysis() {
        return analysis;
    }

    public void setAnalysis(List<OrderHasAnalysis> analysis) {
        this.analysis = analysis;
    }
}
