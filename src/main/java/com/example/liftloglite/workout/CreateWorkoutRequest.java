package com.example.liftloglite.workout;

import java.time.Instant;

public class CreateWorkoutRequest {

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