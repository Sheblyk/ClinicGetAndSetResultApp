package com.example.getandsetResults.service;

import com.example.getandsetResults.entity.Clinic;
import com.example.getandsetResults.model.clinic.ClinicResponse;

import java.util.List;
import java.util.Optional;

public interface IClinicService {
    List<ClinicResponse> getAllClinic();

    Optional<Clinic> find(Long idClinic);
}
