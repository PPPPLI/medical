package com.medical.appointment.utils;

import com.medical.appointment.entity.Doctor;
import com.medical.appointment.enums.DoctorSpecialty;
import org.springframework.data.jpa.domain.Specification;

public class DoctorSpecification {

    public static Specification<Doctor> likeDoctorName(String doctorName) {

        return (root, query, cb) ->
                doctorName == null ? null :
                        cb.equal(root.get("doctorName"), doctorName);
    }


    public static Specification<Doctor> equalDoctorSpecialty(DoctorSpecialty specialty){

        return (root,query,cb) ->
                specialty == null ? null:
                        cb.equal(root.get("doctorSpecialty"), specialty);
    }

    public static Specification<Doctor> isEnable(){

        return (root,query,cb) ->
                cb.isTrue(root.get("doctorStatus"));
    }
}
