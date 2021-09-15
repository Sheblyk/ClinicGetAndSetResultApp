package com.example.getandsetResults.service;

import com.example.getandsetResults.entity.Clinic;
import com.example.getandsetResults.model.clinic.ClinicResponse;

import java.util.List;

public interface IClinicService {
    List<ClinicResponse> getAllClinic();

    Clinic find(Long idClinic);
}
