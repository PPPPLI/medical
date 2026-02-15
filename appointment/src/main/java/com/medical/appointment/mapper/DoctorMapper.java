package com.medical.appointment.mapper;

import com.medical.appointment.dto.DoctorDto;
import com.medical.appointment.entity.Doctor;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface DoctorMapper {

    Doctor doctorDtoToDoctor(DoctorDto doctor);
    DoctorDto doctorToDoctorDto(Doctor doctor);
}
