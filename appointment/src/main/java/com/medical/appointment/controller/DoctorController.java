package com.medical.appointment.controller;

import com.medical.appointment.dto.DoctorDto;
import com.medical.appointment.service.DoctorService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/doctor")
public class DoctorController {

    private final DoctorService doctorService;

    public DoctorController(DoctorService doctorService) {
        this.doctorService = doctorService;
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<DoctorDto> getDoctor(@PathVariable("id") UUID id) {

        return ResponseEntity.ok(doctorService.getDoctorById(id));
    }

    @GetMapping("/get/all")
    public ResponseEntity<List<DoctorDto>> getAllDoctors() {

        return ResponseEntity.ok(doctorService.getAllDoctors());
    }

    @GetMapping("/get/{name}")
    public ResponseEntity<List<DoctorDto>> getDoctorByName(@PathVariable("name") String name) {

        return ResponseEntity.ok(doctorService.getDoctorsByName(name));
    }

    @GetMapping("/get/filter")
    public ResponseEntity<List<DoctorDto>> getDoctorsByFilter(DoctorDto doctorDto) {

        return ResponseEntity.ok(doctorService.getDoctorsByFilter(doctorDto));
    }

    @PostMapping("/add")
    public ResponseEntity<String> addDoctor(@RequestBody DoctorDto doctorDto) {

        doctorService.addDoctor(doctorDto);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/update")
    public ResponseEntity<String> updateDoctor(@RequestBody DoctorDto doctorDto) {

        doctorService.updateDoctor(doctorDto);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteDoctor(@PathVariable("id") UUID id) {

        doctorService.deleteDoctor(id);
        return ResponseEntity.noContent().build();
    }
}
