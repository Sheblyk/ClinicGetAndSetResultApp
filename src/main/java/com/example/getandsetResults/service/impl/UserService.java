package com.example.getandsetResults.service.impl;

import com.example.getandsetResults.AppException;
import com.example.getandsetResults.entity.User;
import com.example.getandsetResults.model.user.UserRequest;
import com.example.getandsetResults.model.user.UserResponse;
import com.example.getandsetResults.repository.ClinicRepository;
import com.example.getandsetResults.repository.RoleRepository;
import com.example.getandsetResults.repository.UserRepository;
import com.example.getandsetResults.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class UserService implements IUserService{

    private final UserRepository userRepo;
    private final RoleRepository roleRepo;
    private final ClinicRepository clinicRepo;

    @Autowired
    public UserService(UserRepository userRepo, RoleRepository roleRepo,
                       ClinicRepository clinicRepo_) {
        this.userRepo = userRepo;
        this.roleRepo = roleRepo;
        this.clinicRepo = clinicRepo_;
    }

    @Override
    @Transactional
    public UserResponse create(UserRequest request) {
        User user = new User(request.surname(), request.name(), request.age());
        user.setRole(roleRepo.getByName("USER"));
        user.setClinic_(clinicRepo.getById(1L));
        return UserResponse.convert(userRepo.save(user));
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<UserResponse> getById(Long id) {
        return userRepo.findById(id).map(UserResponse::convert);
    }

    @Override
    @Transactional
    public void update(Long id, UserRequest request) {
        var user = userRepo.findById(id).orElseThrow(() -> AppException.userNotFound(id));
        user.setName(request.name());
        user.setSurname(request.surname());
        user.setAge(request.age());
    }

    @Override
    @Transactional
    public Optional<UserResponse> delete(Long id) {
        var user = userRepo.findById(id);
        user.ifPresent(userRepo::delete);
        return user.map(UserResponse::convert);
    }
}
