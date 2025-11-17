package com.example.liftloglite.dto;

import jakarta.validation.constraints.NotBlank;

public record CreateExerciseRq(
        @NotBlank String name,
        @NotBlank String muscle,
        @NotBlank String equipment
) { }
