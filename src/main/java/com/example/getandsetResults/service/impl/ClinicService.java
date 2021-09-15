package com.example.getandsetResults.service.impl;

import com.example.getandsetResults.AppException;
import com.example.getandsetResults.entity.Clinic;
import com.example.getandsetResults.model.clinic.ClinicResponse;
import com.example.getandsetResults.repository.ClinicRepository;
import com.example.getandsetResults.service.IClinicService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.stream.Collectors;

public class ClinicService implements IClinicService {

    private final ClinicRepository clinicRepo;

    @Autowired
    public ClinicService(ClinicRepository clinicRepo) {
        this.clinicRepo = clinicRepo;
    }

    @Override
    public List<ClinicResponse> getAllClinic() {
        return clinicRepo.findAll()
                .stream()
                .map(ClinicResponse::convert)
                .collect(Collectors.toList());
    }

    @Override
    public Clinic find(Long idClinic) {
        return clinicRepo.findById(idClinic)
                .orElseThrow(() -> AppException.clinicDoesNotExist(idClinic));
    }
}
