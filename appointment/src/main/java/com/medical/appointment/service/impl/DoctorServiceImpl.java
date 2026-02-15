package com.medical.appointment.service.impl;

import com.medical.appointment.dto.DoctorDto;
import com.medical.appointment.entity.Doctor;
import com.medical.appointment.exception.AuthenticationException;
import com.medical.appointment.mapper.DoctorMapper;
import com.medical.appointment.repository.DoctorRepository;
import com.medical.appointment.service.DoctorService;
import com.medical.appointment.utils.DoctorSpecification;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class DoctorServiceImpl implements DoctorService {

    private final DoctorRepository doctorRepository;
    private final DoctorMapper doctorMapper;

    public DoctorServiceImpl(DoctorRepository doctorRepository, DoctorMapper doctorMapper) {
        this.doctorRepository = doctorRepository;
        this.doctorMapper = doctorMapper;
    }

    @Override
    public DoctorDto getDoctorById(UUID id) {

        return doctorMapper.doctorToDoctorDto(doctorRepository.findDoctorByDoctorIdAndDoctorStatusIsTrue(id));

    }

    @Override
    public List<DoctorDto> getDoctorsByName(String name) {
        return doctorRepository.findAllByDoctorNameAndDoctorStatusIsTrue(name)
                .stream()
                .map(doctorMapper::doctorToDoctorDto)
                .toList();
    }

    @Override
    public List<DoctorDto> getAllDoctors() {
        return doctorRepository.findAll()
                .stream()
                .filter(Doctor::isDoctorStatus)
                .map(doctorMapper::doctorToDoctorDto)
                .toList();
    }

    @Override
    public List<DoctorDto> getDoctorsByIds(List<UUID> ids) {
        return doctorRepository.findAllById(ids)
                .stream()
                .filter(Doctor::isDoctorStatus)
                .map(doctorMapper::doctorToDoctorDto)
                .toList();
    }

    @Override
    public List<DoctorDto> getDoctorsByFilter(DoctorDto doctorDto) {

        Specification<Doctor> spec = DoctorSpecification.isEnable()
                        .and(DoctorSpecification.likeDoctorName(doctorDto.getDoctorName()))
                        .and(DoctorSpecification.equalDoctorSpecialty(doctorDto.getDoctorSpecialty()));

        return doctorRepository.findAll(spec)
                .stream()
                .map(doctorMapper::doctorToDoctorDto)
                .toList();
    }

    @Override
    public void addDoctor(DoctorDto doctorDto) {

        Doctor doctor = doctorMapper.doctorDtoToDoctor(doctorDto);

        doctor.setDoctorCreationAt(LocalDateTime.now());
        doctor.setDoctorStatus(true);

        doctorRepository.save(doctor);
    }

    @Override
    public void updateDoctor(DoctorDto doctorDto) {

        Doctor existDoc = doctorRepository.findDoctorByDoctorIdAndDoctorStatusIsTrue(doctorDto.getDoctorId());

        if(existDoc != null) {

            Doctor updateDoc = doctorMapper.doctorDtoToDoctor(doctorDto);

            updateDoc.setDoctorCreationAt(existDoc.getDoctorCreationAt());
            updateDoc.setDoctorStatus(existDoc.isDoctorStatus());
            doctorRepository.save(updateDoc);

        }


    }

    @Override
    public void deleteDoctor(UUID id) {

        Doctor existDoc = doctorRepository.findDoctorByDoctorIdAndDoctorStatusIsTrue(id);

        if(existDoc != null) {

            existDoc.setDoctorStatus(false);
            doctorRepository.save(existDoc);
        }else{

            throw new AuthenticationException("Doctor not found");
        }
    }
}
