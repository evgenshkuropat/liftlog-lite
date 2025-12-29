package com.example.liftloglite.exercise;

import com.example.liftloglite.common.NotFoundException;
import com.example.liftloglite.dto.CreateExerciseRq;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.UUID;

@Service
public class ExerciseService {

    private final ExerciseRepository repo;

    public ExerciseService(ExerciseRepository repo) {
        this.repo = repo;
    }

    public Exercise create(CreateExerciseRq rq) {
        var ex = new Exercise(
                UUID.randomUUID(),
                rq.name().trim(),
                rq.muscle().trim(),
                rq.equipment().trim()
        );
        return repo.save(ex);
    }

    public Page<Exercise> list(Pageable pageable) {
        return repo.findAll(pageable);
    }

    public List<Exercise> list() {
        return repo.findAll();
    }

    public void deleteExercise(UUID id) {
        // ✅ корректный 404
        if (repo.findById(id).isEmpty()) {
            throw new NotFoundException("Exercise not found: " + id);
        }
        repo.delete(id);
    }
}