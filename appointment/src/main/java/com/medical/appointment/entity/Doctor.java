package com.medical.appointment.entity;

import com.medical.appointment.enums.DoctorSpecialty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity(name = "doctor")
@Table(indexes = {@Index(name = "doctor_name_index",columnList = "doctorName")})
public class Doctor {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID doctorId;
    private String doctorName;
    private String doctorAddress;
    private String cabinetName;
    private String doctorPhone;
    private String doctorEmail;
    @Enumerated(EnumType.STRING)
    private DoctorSpecialty doctorSpecialty;
    private String doctorDescription;
    private boolean doctorStatus;
    private LocalDateTime doctorCreationAt;
}
