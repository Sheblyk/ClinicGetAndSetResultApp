package com.example.getandsetResults.entity;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import javax.persistence.*;
import java.util.Set;
import java.util.UUID;

@Entity
public class Analysis {
    @Id
    @GeneratedValue
    private UUID idAnalysis;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private Double price;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="idCategory", nullable=false)
    private Category category;

    @ManyToMany(mappedBy = "analysis")
    private Set<Order_> orders;

    public Analysis(){}

    public UUID getIdAnalysis() {
        return idAnalysis;
    }

    public void setIdAnalysis(UUID idAnalysis) {
        this.idAnalysis = idAnalysis;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Analysis(UUID idAnalysis, String name, String description, Double price, Category category) {
        this.idAnalysis = idAnalysis;
        this.name = name;
        this.description = description;
        this.price = price;
        this.category = category;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        Analysis analysis = (Analysis) o;

        return new EqualsBuilder()
                .append(idAnalysis, analysis.idAnalysis)
                .append(name, analysis.name)
                .append(description, analysis.description)
                .append(price, analysis.price)
                .append(category, analysis.category)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(idAnalysis)
                .append(name)
                .append(description)
                .append(price)
                .append(category)
                .toHashCode();
    }
}
