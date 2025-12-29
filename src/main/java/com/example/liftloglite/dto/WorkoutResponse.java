package com.example.liftloglite.dto;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

public record WorkoutResponse(
        UUID id,
        Instant startedAt,
        Instant finishedAt,
        List<ExerciseSetsResponse> exercises
) {
    public record ExerciseSetsResponse(UUID exerciseId, List<SetResponse> sets) {}
    public record SetResponse(int setNo, double weightKg, int reps) {}
}