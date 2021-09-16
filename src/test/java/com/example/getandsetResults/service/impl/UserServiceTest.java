package com.example.getandsetResults.service.impl;

import com.example.getandsetResults.entity.Clinic;
import com.example.getandsetResults.entity.Role;
import com.example.getandsetResults.entity.User;
import com.example.getandsetResults.model.user.UserRequest;
import com.example.getandsetResults.model.user.UserResponse;
import com.example.getandsetResults.repository.ClinicRepository;
import com.example.getandsetResults.repository.RoleRepository;
import com.example.getandsetResults.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatExceptionOfType;
import static org.mockito.Mockito.*;

class UserServiceTest {

    private UserRepository userRepo;
    private RoleRepository roleRepo;
    private ClinicRepository clinicRepo;
    private UserService userService;

    @BeforeEach
    void setUp(){
        userRepo = mock(UserRepository.class);
        roleRepo = mock(RoleRepository.class);
        clinicRepo = mock(ClinicRepository.class);
        userService = new UserService(userRepo, roleRepo, clinicRepo);
    }

/*    @Test
    void create() {
        var role = new Role(1L, "USER", new ArrayList<>());
        var clinic = new Clinic(1L, "label", new ArrayList<>(), new ArrayList<>());

        var request = new UserRequest("Surname", "Name", 34);
        var userId = 1L;

        when(userRepo.save(notNull())).thenAnswer(invocation -> {
            User entity = invocation.getArgument(0);
            assertThat(entity.getIdUser()).isNull();
            assertThat(entity.getSurname()).isEqualTo(request.surname());
            assertThat(entity.getName()).isEqualTo(request.name());
            entity.setIdUser(userId);
            return entity;
        });

        UserResponse response = userService.create(request);

        assertThat(response.idUser()).isEqualTo(userId);
        assertThat(response.age()).isEqualTo(request.age());
        assertThat(response.surname()).isEqualTo(request.surname());
        verify(userRepo, only()).save(notNull());
    }*/

    @Test
    void getById() {
        var absentId = 1L;
        var presentId = 2L;
        var role = new Role(1L, "USER", new ArrayList<>());
        var clinic = new Clinic(1L, "label", new ArrayList<>(), new ArrayList<>());
        var user = new User(presentId, "surname", "name", 23, role, new ArrayList<>(), clinic);

        when(userRepo.findById(absentId)).thenReturn(Optional.empty());
        when(userRepo.findById(presentId)).thenReturn(Optional.of(user));

        Optional<UserResponse> absentResponse = userService.getById(absentId);

        assertThat(absentResponse).isEmpty();
        verify(userRepo).findById(absentId);

        Optional<UserResponse> presentResponse = userService.getById(presentId);

        verify(userRepo).findById(presentId);

        verifyNoMoreInteractions(userRepo);
    }

    @Test
    void update() {
        var presentId = 1l;
        var absentId = 2L;
        var update = new UserRequest("newSurname", "newName", 10);

        var role = new Role(1L, "USER", new ArrayList<>());
        var clinic = new Clinic(1L, "label", new ArrayList<>(), new ArrayList<>());
        var user = new User(presentId, "surname", "name", 23, role, new ArrayList<>(), clinic);

        when(userRepo.findById(absentId)).thenReturn(Optional.empty());
        when(userRepo.findById(presentId)).thenReturn(Optional.of(user));
        when(userRepo.save(any(User.class))).thenReturn(user);

        assertThatExceptionOfType(ResponseStatusException.class)
                .isThrownBy(() -> userService.update(absentId, update))
                .satisfies(e -> assertThat(e.getStatus()).isSameAs(HttpStatus.NOT_FOUND));

        verify(userRepo).findById(absentId);

        userService.update(presentId, update);

        assertThat(user.getName()).isEqualTo(update.name());
        assertThat(user.getSurname()).isEqualTo(update.surname());
        assertThat(user.getAge()).isEqualTo(update.age());

        verify(userRepo).findById(presentId);

        verifyNoMoreInteractions(userRepo);
    }

    @Test
    void delete() {
        var presentId = 1l;
        var absentId = 2L;
        var update = new UserRequest("newSurname", "newName", 10);

        var role = new Role(1L, "USER", new ArrayList<>());
        var clinic = new Clinic(1L, "label", new ArrayList<>(), new ArrayList<>());
        var user = new User(presentId, "surname", "name", 23, role, new ArrayList<>(), clinic);

        when(userRepo.findById(absentId)).thenReturn(Optional.empty());
        when(userRepo.findById(presentId)).thenReturn(Optional.of(user));

        Optional<UserResponse> absentResponse = userService.delete(absentId);

        assertThat(absentResponse).isEmpty();
        verify(userRepo).findById(absentId);

        Optional<UserResponse> presentResponse = userService.delete(presentId);

        verify(userRepo).findById(presentId);
        verify(userRepo).delete(user);

        verifyNoMoreInteractions(userRepo);
    }
}