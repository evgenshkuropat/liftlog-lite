package com.example.liftloglite.workout;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.time.Instant;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Workout {

    private final UUID id;
    private final Instant startedAt;
    private final Instant finishedAt;
    private final Map<UUID, List<WorkoutSet>> setsByExercise;

    public Workout(UUID id,
                   Instant startedAt,
                   Instant finishedAt,
                   Map<UUID, List<WorkoutSet>> setsByExercise) {
        this.id = id;
        this.startedAt = startedAt;
        this.finishedAt = finishedAt;
        this.setsByExercise = setsByExercise;
    }

    public UUID getId() {
        return id;
    }

    public Instant getStartedAt() {
        return startedAt;
    }

    public Instant getFinishedAt() {
        return finishedAt;
    }

    public Map<UUID, List<WorkoutSet>> getSetsByExercise() {
        return setsByExercise;
    }
}