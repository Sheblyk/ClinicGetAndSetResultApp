package com.example.getandsetResults.entity;

import lombok.*;
import org.springframework.boot.context.properties.bind.DefaultValue;

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
public class Order_ {
    @Id
    @GeneratedValue
    private Long idOrder;

    @Column(nullable = false)
    private final Timestamp time = Timestamp.valueOf(LocalDateTime.now());

    @Column(nullable = false)
    private Double priceForOrder;

    @Column(columnDefinition = "bit default false")
    private boolean finished;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "order_")
    private List<OrderHasAnalysis> analysis = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idUser", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idClinic", nullable = false)
    private Clinic clinic;
}
