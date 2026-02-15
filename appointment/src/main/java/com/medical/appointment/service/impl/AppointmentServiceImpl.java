package com.medical.appointment.service.impl;

import com.medical.appointment.dto.AppointmentDto;
import com.medical.appointment.dto.DoctorDto;
import com.medical.appointment.entity.Appointment;
import com.medical.appointment.enums.AppointStatus;
import com.medical.appointment.exception.BusinessException;
import com.medical.appointment.mapper.AppointmentMapper;
import com.medical.appointment.repository.AppointmentRepository;
import com.medical.appointment.service.AppointmentService;
import com.medical.appointment.service.DoctorService;
import com.medical.appointment.utils.AppointmentSpecification;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class AppointmentServiceImpl implements AppointmentService {

    private final AppointmentRepository appointmentRepository;
    private final AppointmentMapper appointmentMapper;
    private final DoctorService doctorService;

    public AppointmentServiceImpl(AppointmentRepository appointmentRepository, AppointmentMapper appointmentMapper,
                                  DoctorService doctorService) {
        this.appointmentRepository = appointmentRepository;
        this.appointmentMapper = appointmentMapper;
        this.doctorService = doctorService;
    }

    @Override
    public List<AppointmentDto> getAllAppointmentsByPatientId(UUID patientId) {

        List<Appointment> appointments = appointmentRepository.findAllByPatientId(patientId);
        return setDoctorAndToDto(appointments);
    }

    @Override
    public List<AppointmentDto> getAllAppointmentByDate(AppointmentDto appointmentDto) {
        Specification<Appointment> spect = AppointmentSpecification.afterStartDate(appointmentDto.getStartAt())
                .and(AppointmentSpecification.BeforeEndDate(appointmentDto.getEndAt()));

        List<Appointment> appointments = appointmentRepository.findAll(spect);
        return setDoctorAndToDto(appointments);
    }

    @Override
    public List<AppointmentDto> getAllAppointmentsByFilter(AppointmentDto appointmentDto) {

        Specification<Appointment> spec = AppointmentSpecification.equalDoctorId(appointmentDto.getDoctorDto().getDoctorId())
                .and(AppointmentSpecification.afterStartDate(appointmentDto.getStartAt()));

        List<Appointment> appointments = appointmentRepository.findAll(spec);
        return setDoctorAndToDto(appointments);
    }

    @Override
    public void addAppointment(AppointmentDto appointmentDto) {

        Appointment appointment = appointmentRepository.findAppointmentByDoctorIdAndStartAt(
                appointmentDto.getDoctorDto().getDoctorId(), appointmentDto.getStartAt()
        );

        if(appointment != null && !appointment.getStatus().equals(AppointStatus.CANCELED)) {

            throw new BusinessException("Appointment is not available");
        }

        List<Appointment> appointmentList = appointmentRepository.findAllByPatientId(appointmentDto.getPatientId());

        boolean hasAppInSameDay = appointmentList.stream()
                .filter(app-> app.getStatus().equals(AppointStatus.RESERVED))
                .anyMatch(app -> app.getStartAt().toLocalDate().isEqual(appointmentDto.getStartAt().toLocalDate()));

        if(hasAppInSameDay) {
            throw new BusinessException("You have already been appointment in this day");
        }

        Appointment appointmentToSave = appointmentMapper.dtoToAppointment(appointmentDto);
        appointmentToSave.setDoctorId(appointmentDto.getDoctorDto().getDoctorId());
        appointmentToSave.setCreateAt(LocalDateTime.now());
        appointmentToSave.setUpdateAt(LocalDateTime.now());
        appointmentToSave.setStatus(AppointStatus.RESERVED);
        appointmentRepository.save(appointmentToSave);
    }

    @Override
    public void updateAppointment(AppointmentDto appointmentDto) {

        Appointment existingAppointment = appointmentRepository.findById(appointmentDto.getAppointmentId()).orElse(null);

        if(existingAppointment == null) {
            throw new BusinessException("Appointment does not exist");
        }

        existingAppointment.setStartAt(appointmentDto.getStartAt());
        existingAppointment.setEndAt(appointmentDto.getEndAt());
        existingAppointment.setUpdateAt(LocalDateTime.now());
        appointmentRepository.save(existingAppointment);
    }

    @Override
    public void deleteAppointment(UUID id) {

        Appointment existingAppointment = appointmentRepository.findById(id).orElse(null);
        if(existingAppointment == null) {
            throw new BusinessException("Appointment does not exist");
        }

        existingAppointment.setStatus(AppointStatus.CANCELED);
        appointmentRepository.save(existingAppointment);

    }

    private List<AppointmentDto> setDoctorAndToDto(List<Appointment> appointments) {

        Set<UUID> doctorIds = appointments.stream().map(Appointment::getDoctorId).collect(Collectors.toSet());

        Map<UUID,DoctorDto> doctorMap = doctorService.getDoctorsByIds(doctorIds.stream().toList())
                .stream()
                .collect(Collectors.toMap(DoctorDto::getDoctorId, doctor -> doctor));

        List<AppointmentDto> appointmentDtos = new ArrayList<>();

        appointments.forEach(app -> {

            AppointmentDto appointmentDto = appointmentMapper.appointmentToDto(app);
            appointmentDto.setDoctorDto(doctorMap.get(app.getDoctorId()));
            appointmentDtos.add(appointmentDto);
        });

        return appointmentDtos;
    }
}
