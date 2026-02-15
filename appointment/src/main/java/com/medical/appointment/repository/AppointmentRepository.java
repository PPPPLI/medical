package com.medical.appointment.repository;

import com.medical.appointment.entity.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, UUID>, JpaSpecificationExecutor<Appointment> {

    List<Appointment> findAllByPatientId(UUID patientId);
    Appointment findAppointmentByDoctorIdAndStartAt(UUID doctorId, LocalDateTime startAt);
}
