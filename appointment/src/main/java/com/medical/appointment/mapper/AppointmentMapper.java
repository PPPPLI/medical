package com.medical.appointment.mapper;

import com.medical.appointment.dto.AppointmentDto;
import com.medical.appointment.entity.Appointment;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AppointmentMapper {

    Appointment dtoToAppointment(AppointmentDto dto);
    AppointmentDto appointmentToDto(Appointment appointment);
}
