package com.example.liftloglite.exercise;

import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class ExerciseRepository {
    private final Map<java.util.UUID, Exercise> storage = new ConcurrentHashMap<>();

    public Exercise save(Exercise e) { storage.put(e.getId(), e); return e; }
    public Optional<Exercise> findById(java.util.UUID id) { return Optional.ofNullable(storage.get(id)); }
    public List<Exercise> findAll() { return storage.values().stream().toList(); }
}
