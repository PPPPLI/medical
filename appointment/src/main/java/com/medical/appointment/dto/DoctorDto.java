package com.medical.appointment.dto;

import com.medical.appointment.enums.DoctorSpecialty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DoctorDto {

    private UUID doctorId;
    private String doctorName;
    private String doctorAddress;
    private String cabinetName;
    private String doctorPhone;
    private String doctorEmail;
    private DoctorSpecialty doctorSpecialty;
    private String doctorDescription;
}
