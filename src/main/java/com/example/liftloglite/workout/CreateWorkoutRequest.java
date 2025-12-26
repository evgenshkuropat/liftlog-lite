package com.example.liftloglite.workout.dto;

import jakarta.validation.constraints.NotNull;
import java.time.Instant;

public class CreateWorkoutRequest {
    @NotNull
    private Instant startedAt;

    public Instant getStartedAt() { return startedAt; }
    public void setStartedAt(Instant startedAt) { this.startedAt = startedAt; }
}
