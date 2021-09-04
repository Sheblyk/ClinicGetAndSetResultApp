package com.example.getandsetResults.service;

import com.example.getandsetResults.model.user.UserRequest;
import com.example.getandsetResults.model.user.UserResponse;

import java.util.Optional;

public interface IUserService {
    UserResponse create(UserRequest request);

    Optional<UserResponse> getById(Long id);

    void update(Long id, UserRequest request);

    Optional<UserResponse> delete(Long id);
}
