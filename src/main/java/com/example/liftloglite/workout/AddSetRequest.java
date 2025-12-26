package com.example.liftloglite.workout.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

public class AddSetRequest {

    @NotBlank
    private String exerciseName;

    @Min(1)
    private int reps;

    @Positive
    private double weight;

    // getters/setters
}
