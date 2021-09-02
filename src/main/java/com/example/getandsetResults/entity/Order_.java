package com.example.getandsetResults.entity;

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;

@Entity
public class Order_ {
    @Id
    @GeneratedValue
    private UUID idOrder;

    @Column(nullable = false)
    private final Timestamp time = Timestamp.valueOf(LocalDateTime.now());

    @Column(nullable = false)
    private Double priceForOrder;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "order_has_analysis",
    joinColumns = @JoinColumn(name = "fk_analysis"),
    inverseJoinColumns = @JoinColumn(name = "fk_order")
    )
    private Set<Analysis> analysis;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="idUser", nullable=false)
    private User user;

    public Order_(){}

    public UUID getIdOrder() {
        return idOrder;
    }

    public void setIdOrder(UUID idOrder) {
        this.idOrder = idOrder;
    }

    public Timestamp getTime() {
        return time;
    }

    public Set<Analysis> getAnalysis() {
        return analysis;
    }

    public void setAnalysis(Set<Analysis> analysis) {
        this.analysis = analysis;
    }
}
