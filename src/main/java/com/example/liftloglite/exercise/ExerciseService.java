package com.example.liftloglite.exercise;

import com.example.liftloglite.dto.CreateExerciseRq;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExerciseService {
    private final ExerciseRepository repo;

    public ExerciseService(ExerciseRepository repo) {
        this.repo = repo;
    }

    public Exercise create(CreateExerciseRq rq) {
        var ex = new Exercise(java.util.UUID.randomUUID(),
                rq.name().trim(),
                rq.muscle().trim(),
                rq.equipment().trim());
        return repo.save(ex);
    }

    public List<Exercise> list() { return repo.findAll(); }
}
