package com.example.getandsetResults.entity;

import lombok.*;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
//@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
public class Category {
    @Id
    @GeneratedValue
    private Long idCategory;

    @Column(nullable = false)
    private String categoryName;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "category")
    private List<Analysis> analyses = new ArrayList<>();

    @Override
    public String toString(){
        return "id: " + idCategory +
        " categoryName" + categoryName +
        " analysis " + analyses.toString();
    }
}
