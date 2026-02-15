package com.medical.appointment.repository;

import com.medical.appointment.entity.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface DoctorRepository extends JpaRepository<Doctor, UUID>, JpaSpecificationExecutor<Doctor> {

    List<Doctor> findAllByDoctorNameAndDoctorStatusIsTrue(String doctorName);
    Doctor findDoctorByDoctorIdAndDoctorStatusIsTrue(UUID doctorId);

}
