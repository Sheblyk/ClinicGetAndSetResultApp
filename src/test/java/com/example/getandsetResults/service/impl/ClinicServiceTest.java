package com.example.getandsetResults.service.impl;

import com.example.getandsetResults.AppException;
import com.example.getandsetResults.entity.Clinic;
import com.example.getandsetResults.model.clinic.ClinicResponse;
import com.example.getandsetResults.repository.ClinicRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

class ClinicServiceTest {

    private ClinicRepository clinicRepo;
    private ClinicService clinicService;

    @BeforeEach
    void setUp() {
        clinicRepo = mock(ClinicRepository.class);
        clinicService = new ClinicService(clinicRepo);
    }

    @Test
    void getAllClinic() {
        Clinic clinic1 = new Clinic(1L, "Puska", new ArrayList<>(), new ArrayList<>());
        Clinic clinic2 = new Clinic(2L, "Puska 2", new ArrayList<>(), new ArrayList<>());

        List<Clinic> clinics = new ArrayList<>();
        clinics.add(clinic1);
        clinics.add(clinic2);

        when(clinicRepo.findAll()).thenReturn(clinics);

        List<ClinicResponse> clinicGETTED = clinicService.getAllClinic();

        assertEquals(2, clinicGETTED.size());
        verify(clinicRepo, times(1)).findAll();
    }

    @Test
    void find() {
        Long presentId = 1L;
        Long absentId = 2L;
        var clinic = new Clinic(1L, "Puska", new ArrayList<>(), new ArrayList<>());

        when(clinicRepo.findById(absentId)).thenThrow(AppException.clinicDoesNotExist(absentId));
        when(clinicRepo.findById(presentId)).thenReturn(Optional.of(clinic));

        Optional<Clinic> presentResponse = clinicService.find(presentId);
        assertThat(presentResponse.get()).isEqualTo(clinic);
    }
}