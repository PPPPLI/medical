package com.medical.appointment.dto;

import com.medical.appointment.enums.AppointStatus;
import jakarta.validation.constraints.NotNull;
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
public class AppointmentDto {

    private UUID appointmentId;
    private DoctorDto doctorDto;
    @NotNull(message = "patient required")
    private UUID patientId;
    @NotNull(message = "start time required")
    private LocalDateTime startAt;
    @NotNull(message = "end time required")
    private LocalDateTime endAt;
    private AppointStatus status;
}
