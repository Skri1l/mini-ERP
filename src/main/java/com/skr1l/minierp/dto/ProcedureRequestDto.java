package com.skr1l.minierp.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import java.math.BigDecimal;

public record ProcedureRequestDto(

        @NotBlank
        String procedureName,

        @NotBlank
        String description,

        @NotNull
        @Positive
        BigDecimal price,

        @Positive
        int durationMinutes
)
{}
