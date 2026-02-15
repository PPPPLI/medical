package com.medical.appointment.utils;

import com.medical.appointment.entity.Appointment;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDateTime;
import java.util.UUID;

public class AppointmentSpecification {

    public static Specification<Appointment> equalDoctorId(UUID doctorId){

        return (root,query,cb) -> doctorId == null ? null:
                cb.equal(root.get("doctorId"), doctorId);
    }

    public static Specification<Appointment> afterStartDate(LocalDateTime startDate){

        return (root,query,cb) -> cb.greaterThanOrEqualTo(root.get("startAt"), startDate);
    }

    public static Specification<Appointment> BeforeEndDate(LocalDateTime endDate){

        return (root,query,cb) -> cb.lessThanOrEqualTo(root.get("endAt"), endDate);
    }

    public static Specification<Appointment> equalPatientId(UUID patientId){

        return (root,query,cb) -> cb.equal(root.get("patientId"), patientId);
    }

}
