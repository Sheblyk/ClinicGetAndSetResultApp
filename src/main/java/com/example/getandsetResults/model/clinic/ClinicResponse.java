package com.example.getandsetResults.model.clinic;

import com.example.getandsetResults.entity.Clinic;

public record ClinicResponse(Long idClinic, String address) {

    public static ClinicResponse convert(Clinic clinic){
        return new ClinicResponse(clinic.getIdClinic(),
                clinic.getAddress());
    }
}
