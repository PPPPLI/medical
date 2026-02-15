package com.medical.appointment.controller;

import com.medical.appointment.dto.AppointmentDto;
import com.medical.appointment.service.AppointmentService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/appointment")
public class AppointmentController {

    private final AppointmentService appointmentService;
    public AppointmentController(AppointmentService appointmentService) {
        this.appointmentService = appointmentService;
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<List<AppointmentDto>> getAppointment(@PathVariable("id") UUID id) {

        return ResponseEntity.ok(appointmentService.getAllAppointmentsByPatientId(id));
    }

    @GetMapping("/get/filter")
    public ResponseEntity<List<AppointmentDto>> getAppointmentFilter(AppointmentDto appointmentDto) {

        return ResponseEntity.ok(appointmentService.getAllAppointmentsByFilter(appointmentDto));
    }

    @GetMapping("/get/date")
    public ResponseEntity<List<AppointmentDto>> getAppointmentDate(AppointmentDto appointmentDto) {

        return ResponseEntity.ok(appointmentService.getAllAppointmentByDate(appointmentDto));
    }

    @PostMapping("/add")
    public ResponseEntity<String> addAppointment(@RequestBody @Validated AppointmentDto appointmentDto) {

        appointmentService.addAppointment(appointmentDto);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/update")
    public ResponseEntity<String> updateAppointment(@RequestBody @Validated AppointmentDto appointmentDto) {

        appointmentService.updateAppointment(appointmentDto);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteAppointment(@PathVariable("id") UUID id) {

        appointmentService.deleteAppointment(id);
        return ResponseEntity.noContent().build();
    }
}
