package com.example.liftloglite.workout;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;

import java.time.Instant;

public class CreateWorkoutRequest {

    @Schema(
            description = "Workout start time",
            example = "2025-01-01T10:00:00Z"
    )
    @NotNull(message = "startedAt must not be null")
    private Instant startedAt;

    public CreateWorkoutRequest() {
    }

    public Instant getStartedAt() {
        return startedAt;
    }

    public void setStartedAt(Instant startedAt) {
        this.startedAt = startedAt;
    }
}