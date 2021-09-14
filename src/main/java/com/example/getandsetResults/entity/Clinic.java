package com.example.getandsetResults.entity;

import lombok.*;

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
public class Clinic {
    @Id
    @GeneratedValue
    private Long idClinic;

    private String address;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "clinic")
    private List<Order_> orders = new ArrayList<>();

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "clinic_")
    private List<User> users = new ArrayList<>();
}
