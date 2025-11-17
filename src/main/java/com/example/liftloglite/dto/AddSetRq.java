package com.example.liftloglite.dto;

import jakarta.validation.constraints.*;
import java.math.BigDecimal;
import java.util.UUID;

public record AddSetRq(
        @NotNull UUID workoutId,
        @NotNull UUID exerciseId,
        @NotNull @Positive BigDecimal weightKg,
        @NotNull @Min(1) @Max(50) Integer reps
) { }
