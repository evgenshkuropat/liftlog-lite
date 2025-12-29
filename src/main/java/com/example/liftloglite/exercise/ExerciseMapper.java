package com.example.liftloglite.exercise;

import com.example.liftloglite.dto.ExerciseResponse;

public class ExerciseMapper {

    public static ExerciseResponse toResponse(Exercise exercise) {
        return new ExerciseResponse(exercise.getId(), exercise.getName());
    }
}