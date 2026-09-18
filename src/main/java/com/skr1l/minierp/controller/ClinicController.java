package com.skr1l.minierp.controller;

import com.skr1l.minierp.dto.ClinicRequestDto;
import com.skr1l.minierp.entity.Clinic;
import com.skr1l.minierp.service.clinic.ClinicService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/clinics")
public class ClinicController {

    private final ClinicService clinicService;

    @GetMapping("/{id}")
    public Clinic getClinicById(@PathVariable Long id){
        Clinic clinic = clinicService.getClinic(id);

        return clinic;
    }

    @PostMapping
    public Long createClinic(@Valid @RequestBody ClinicRequestDto clinicDto){
        return clinicService.createClinic(clinicDto);
    }
}
