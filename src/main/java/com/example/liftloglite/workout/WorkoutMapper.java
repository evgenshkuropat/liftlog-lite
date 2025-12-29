package com.example.liftloglite.workout;

import com.example.liftloglite.dto.WorkoutResponse;

import java.util.List;

public class WorkoutMapper {

    public static WorkoutResponse toResponse(Workout workout) {

        var exercises = workout.getSetsByExercise().entrySet().stream()
                .map(entry -> new WorkoutResponse.ExerciseSetsResponse(
                        entry.getKey(),
                        entry.getValue().stream()
                                .map(set -> new WorkoutResponse.SetResponse(
                                        set.getSetNo(),
                                        set.getWeightKg(),
                                        set.getReps()
                                ))
                                .toList()
                ))
                .toList();

        return new WorkoutResponse(
                workout.getId(),
                workout.getStartedAt(),
                workout.getFinishedAt(),
                exercises
        );
    }
}