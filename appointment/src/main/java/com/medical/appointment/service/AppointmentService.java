package com.medical.appointment.service;

import com.medical.appointment.dto.AppointmentDto;

import java.util.List;
import java.util.UUID;

public interface AppointmentService {

    List<AppointmentDto> getAllAppointmentsByPatientId(UUID patientId);
    List<AppointmentDto> getAllAppointmentsByFilter(AppointmentDto appointmentDto);
    List<AppointmentDto> getAllAppointmentByDate(AppointmentDto appointmentDto);
    void addAppointment(AppointmentDto appointmentDto);
    void updateAppointment(AppointmentDto appointmentDto);
    void deleteAppointment(UUID id);
}
