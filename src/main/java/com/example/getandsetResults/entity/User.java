package com.example.getandsetResults.entity;

import lombok.*;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
public class User {
    @Id
    @GeneratedValue
    private Long idUser;

    @Column(nullable = false)
    private String surname;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private Integer age;

    public User(String surname, String name, Integer age){
        this.surname = surname;
        this.name = name;
        this.age = age;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idRole", nullable = false)
    private Role role;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
    private List<Order_> orders = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="idClinic", nullable=false)
    private Clinic clinic_;

    public User(long presentId, String surname, String name, int i) {
        this.idUser = presentId;
        this.surname = surname;
        this.name = name;
        this.age = i;
    }
}
