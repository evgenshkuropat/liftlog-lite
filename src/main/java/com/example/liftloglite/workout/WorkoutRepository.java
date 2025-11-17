package com.example.liftloglite.workout;

import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class WorkoutRepository {
    private final Map<java.util.UUID, Workout> storage = new ConcurrentHashMap<>();

    public Workout save(Workout w) { storage.put(w.getId(), w); return w; }
    public Optional<Workout> findById(java.util.UUID id) { return Optional.ofNullable(storage.get(id)); }
    public List<Workout> findAll() { return storage.values().stream().toList(); }
}
