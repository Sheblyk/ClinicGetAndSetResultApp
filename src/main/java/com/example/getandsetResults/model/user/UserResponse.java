package com.example.getandsetResults.model.user;

import com.example.getandsetResults.entity.User;

public record UserResponse(Long idUser,
                           String surname,
                           String name,
                           Integer age,
                           String role) {

    public static UserResponse convert(User user) {
        return new UserResponse(user.getIdUser(),
                user.getSurname(),
                user.getName(),
                user.getAge(),
                user.getRole().getRoleName());
    }
}
