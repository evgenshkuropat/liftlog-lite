package com.example.liftloglite.workout;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.util.UUID;

public class AddSetRequest {

    @Schema(
            description = "Exercise ID",
            example = "550e8400-e29b-41d4-a716-446655440000"
    )
    @NotNull
    private UUID exerciseId;

    @Schema(example = "60")
    @Positive
    private double weightKg;

    @Schema(example = "10")
    @Min(1)
    private int reps;

    public AddSetRequest() {}

    public UUID getExerciseId() {
        return exerciseId;
    }

    public void setExerciseId(UUID exerciseId) {
        this.exerciseId = exerciseId;
    }

    public double getWeightKg() {
        return weightKg;
    }

    public void setWeightKg(double weightKg) {
        this.weightKg = weightKg;
    }

    public int getReps() {
        return reps;
    }

    public void setReps(int reps) {
        this.reps = reps;
    }
}