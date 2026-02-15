package com.medical.appointment.service;

import com.medical.appointment.dto.DoctorDto;
import com.medical.appointment.enums.DoctorSpecialty;

import java.util.List;
import java.util.UUID;

public interface DoctorService {

    DoctorDto getDoctorById(UUID id);
    List<DoctorDto> getDoctorsByName(String name);
    List<DoctorDto> getAllDoctors();
    List<DoctorDto> getDoctorsByIds(List<UUID> ids);
    List<DoctorDto> getDoctorsByFilter(DoctorDto doctorDto);
    void addDoctor(DoctorDto doctorDto);
    void updateDoctor(DoctorDto doctorDto);
    void deleteDoctor(UUID id);
}
