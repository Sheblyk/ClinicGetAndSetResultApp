package com.example.getandsetResults.entity;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
public class Category {
    @Id
    @GeneratedValue
    private Long idCategory;

    @Column(nullable = false)
    private String categoryName;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "category")
    private List<Analysis> analyses = new ArrayList<>();

    public Category(){}

    public Category(Long idCategory, String categoryName, List<Analysis> analyses) {
        this.idCategory = idCategory;
        this.categoryName = categoryName;
        this.analyses = analyses;
    }

    public Long getIdCategory() {
        return idCategory;
    }

    public void setIdCategory(Long idCategory) {
        this.idCategory = idCategory;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public List<Analysis> getAnalyses() {
        return analyses;
    }

    public void setAnalyses(List<Analysis> analyses) {
        this.analyses = analyses;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        Category category = (Category) o;

        return new EqualsBuilder()
                .append(idCategory, category.idCategory)
                .append(categoryName, category.categoryName)
                .append(analyses, category.analyses)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(idCategory)
                .append(categoryName)
                .append(analyses)
                .toHashCode();
    }
}
