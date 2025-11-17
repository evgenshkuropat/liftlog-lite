package com.example.liftloglite.dto;

import jakarta.validation.constraints.NotNull;
import java.time.Instant;

public record CreateWorkoutRq(@NotNull Instant startedAt) { }
