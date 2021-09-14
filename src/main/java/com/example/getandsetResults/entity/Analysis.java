package com.example.getandsetResults.entity;

import lombok.*;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
public class Analysis {
    @Id
    @GeneratedValue
    private Long idAnalysis;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private Double price;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="idCategory", nullable=false)
    private Category category;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "analysis")
    private List<OrderHasAnalysis> analysis = new ArrayList<>();

    public Analysis(Long idAnalysis, String name, String description, Double price, Category category) {
        this.idAnalysis = idAnalysis;
        this.name = name;
        this.description = description;
        this.price = price;
        this.category = category;
    }

}
