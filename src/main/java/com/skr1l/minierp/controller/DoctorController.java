package com.skr1l.minierp.controller;

import com.skr1l.minierp.dto.DoctorRequestDto;
import com.skr1l.minierp.entity.Doctor;
import com.skr1l.minierp.service.Doctor.DoctorService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class DoctorController {

    private final DoctorService doctorService;

    @GetMapping("/doctors/{doctorId}")
    public Doctor getDoctorById(@PathVariable Long doctorId){

        return doctorService.getDoctorById(doctorId);
    }

    @PostMapping("/clinics/{clinicId}/doctors")
    public Long addDoctorToClinic(@Valid @RequestBody DoctorRequestDto doctorDto,
                                  @PathVariable Long clinicId){

        return doctorService.addDoctorToClinic(doctorDto, clinicId);
    }

    @GetMapping("/clinics/{clinicId}/doctors")
    public List<Doctor> getDoctorsByClinicId(@PathVariable Long clinicId){

        return doctorService.getDoctorsByClinicId(clinicId);
    }

    @PutMapping("/doctors/{doctorId}")
    public void updateDoctorById(@RequestBody @Valid DoctorRequestDto doctorDto,
                                 @PathVariable Long doctorId){

        doctorService.updateDoctor(doctorDto, doctorId);
    }

    @PatchMapping("/doctors/{doctorId}/deactivate")
    public void deactivateDoctorById(@PathVariable Long doctorId){

        doctorService.deactivateDoctor(doctorId);
    }
}
