package com.example.getandsetResults.entity;

import lombok.*;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
public class Role {
    @Id
    @GeneratedValue
    private Long idRole;

    @Column(nullable = false)
    private String roleName;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "role")
    private List<User> userEntities = new ArrayList<>();
}
