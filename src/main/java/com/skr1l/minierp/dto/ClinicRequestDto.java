package com.skr1l.minierp.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;

public record ClinicRequestDto (

        @Column(nullable = false, length = 100)
        @NotBlank String clinicName,

        @Column(nullable = false, unique = true, length = 20)
        @NotBlank String phoneNumber,

        @Column(nullable = false, length = 50)
        @NotBlank String address
)
{}
