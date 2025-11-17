package com.example.liftloglite.workout;

import com.example.liftloglite.dto.AddSetRq;
import com.example.liftloglite.dto.CreateWorkoutRq;
import com.example.liftloglite.exercise.ExerciseRepository;
import com.example.liftloglite.workout.model.SetEntry;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WorkoutService {
    private final WorkoutRepository repo;
    private final ExerciseRepository exerciseRepo;

    public WorkoutService(WorkoutRepository repo, ExerciseRepository exerciseRepo) {
        this.repo = repo;
        this.exerciseRepo = exerciseRepo;
    }

    public Workout create(CreateWorkoutRq rq) {
        var w = new Workout(java.util.UUID.randomUUID(), rq.startedAt());
        return repo.save(w);
    }

    public Workout addSet(AddSetRq rq) {
        var w = repo.findById(rq.workoutId())
                .orElseThrow(() -> new java.util.NoSuchElementException("Workout not found"));
        var ex = exerciseRepo.findById(rq.exerciseId())
                .orElseThrow(() -> new java.util.NoSuchElementException("Exercise not found"));
        int next = nextSetNo(w, ex.getId());
        w.addSet(ex.getId(), new SetEntry(next, rq.weightKg(), rq.reps()));
        return w;
    }

    private int nextSetNo(Workout w, java.util.UUID exId) {
        var list = w.getSetsByExercise().getOrDefault(exId, java.util.List.of());
        return list.size() + 1;
    }

    public List<Workout> list() { return repo.findAll(); }
}
