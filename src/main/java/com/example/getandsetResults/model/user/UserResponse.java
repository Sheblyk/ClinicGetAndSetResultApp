package com.example.getandsetResults.model.user;

import com.example.getandsetResults.entity.User;

import java.util.UUID;

public class UserResponse {
    private UUID idUser;
    private String surname;
    private String name;
    private Integer age;
    private String role;

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

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public UserResponse(UUID idUser, String surname, String name, Integer age, String role) {
        this.idUser = idUser;
        this.surname = surname;
        this.name = name;
        this.age = age;
        this.role = role;
    }

    public static UserResponse convert(User user) {
        return new UserResponse(user.getIdUser(),
                user.getSurname(),
                user.getName(),
                user.getAge(),
                user.getRole().getRoleName());
    }
}
