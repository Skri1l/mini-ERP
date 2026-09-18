package com.skr1l.minierp.dto;

import com.skr1l.minierp.entity.Clinic;
import jakarta.validation.constraints.NotBlank;

public record DoctorRequestDto(

        @NotBlank
         String firstName,

        @NotBlank
        String lastName,

        @NotBlank
        String specialization,

        @NotBlank
        String phoneNumber,

        @NotBlank
        String email,

        String photoUrl,

        boolean isActive
)
{}
