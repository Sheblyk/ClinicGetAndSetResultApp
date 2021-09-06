package com.example.getandsetResults.controller;

import com.example.getandsetResults.AppException;
import com.example.getandsetResults.model.user.UserRequest;
import com.example.getandsetResults.model.user.UserResponse;
import com.example.getandsetResults.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/user")
public class UserController {

    private final IUserService userService;

    @Autowired
    public UserController(IUserService iUserService) {
        this.userService = iUserService;
    }

    @GetMapping("/{id}")
    public UserResponse get(@PathVariable Long id) {
        return userService.getById(id)
                .orElseThrow(() -> AppException.userNotFound(id));
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public UserResponse create(@Valid @RequestBody UserRequest request) {
        return userService.create(request);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping("/{id}")
    public void update(@PathVariable Long id, @Valid @RequestBody UserRequest request) {
        userService.update(id, request);
    }

    @DeleteMapping("/{id}")
    public UserResponse delete(@PathVariable Long id) {
        return userService.delete(id)
                .orElseThrow(() -> AppException.userNotFound(id));
    }

}
