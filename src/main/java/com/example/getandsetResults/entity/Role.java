package com.example.getandsetResults.entity;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
public class Role {
    @Id
    @GeneratedValue
    private Long idRole;

    @Column(nullable = false)
    private String roleName;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "role")
    private List<User> userEntities = new ArrayList<>();

    public Role(){}

    public List<User> getUserEntities() {
        return userEntities;
    }

    public void setUserEntities(List<User> userEntities) {
        this.userEntities = userEntities;
    }

    public Role(String roleName) {
        this.roleName = roleName;
    }

    public Long getIdRole() {
        return idRole;
    }

    public void setIdRole(Long idRole) {
        this.idRole = idRole;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        Role role = (Role) o;

        return new EqualsBuilder()
                .append(idRole, role.idRole)
                .append(roleName, role.roleName)
                .append(userEntities, role.userEntities)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(idRole)
                .append(roleName)
                .append(userEntities)
                .toHashCode();
    }
}
