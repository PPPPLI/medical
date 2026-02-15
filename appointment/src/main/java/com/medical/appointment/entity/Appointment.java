package com.medical.appointment.entity;

import com.medical.appointment.enums.AppointStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity(name = "appointment")
@Table(indexes = {@Index(name = "appointment_doctorId_startDate",columnList = "doctorId,startAt")})
public class Appointment {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID appointmentId;
    private UUID patientId;
    private UUID doctorId;
    private LocalDateTime startAt;
    private LocalDateTime endAt;
    private LocalDateTime createAt;
    private LocalDateTime updateAt;
    @Enumerated(EnumType.STRING)
    private AppointStatus status;
}
