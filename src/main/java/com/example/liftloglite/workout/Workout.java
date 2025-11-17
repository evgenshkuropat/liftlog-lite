package com.example.liftloglite.workout;

import com.example.liftloglite.workout.model.SetEntry;

import java.time.Instant;
import java.util.*;

public class Workout {
    private final java.util.UUID id;
    private final Instant startedAt;
    private Instant finishedAt;
    private final Map<java.util.UUID, java.util.List<SetEntry>> setsByExercise = new LinkedHashMap<>();

    public Workout(java.util.UUID id, Instant startedAt) {
        this.id = id;
        this.startedAt = startedAt;
    }

    public void addSet(java.util.UUID exerciseId, SetEntry s) {
        setsByExercise.computeIfAbsent(exerciseId, k -> new ArrayList<>()).add(s);
    }

    public java.util.UUID getId() { return id; }
    public Instant getStartedAt() { return startedAt; }
    public Instant getFinishedAt() { return finishedAt; }
    public void finish(Instant finishedAt) { this.finishedAt = finishedAt; }
    public Map<java.util.UUID, java.util.List<SetEntry>> getSetsByExercise() { return setsByExercise; }
}
