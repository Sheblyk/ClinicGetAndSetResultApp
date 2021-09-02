package com.example.getandsetResults.entity;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Entity
public class User {
    @Id
    @GeneratedValue
    private UUID idUser;

    @Column(nullable = false)
    private String surname;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private Integer age;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="idRole", nullable=false)
    private Role role;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
    private List<Order_> orders = new ArrayList<>();

    public User(){}

    public User(String surname, String name, Integer age) {
        this.surname = surname;
        this.name = name;
        this.age = age;
    }

    public List<Order_> getOrders() {
        return orders;
    }

    public void setOrders(List<Order_> orders) {
        this.orders = orders;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return new EqualsBuilder()
                .append(idUser, user.idUser)
                .append(surname, user.surname)
                .append(name, user.name)
                .append(age, user.age)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(idUser)
                .append(surname)
                .append(name)
                .append(age)
                .toHashCode();
    }

    public UUID getIdUser() {
        return idUser;
    }

    public void setIdUser(UUID idUser) {
        this.idUser = idUser;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
