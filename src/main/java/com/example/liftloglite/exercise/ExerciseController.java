package com.example.liftloglite.exercise;

import com.example.liftloglite.dto.CreateExerciseRq;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/exercises")
public class ExerciseController {
    private final ExerciseService service;

    public ExerciseController(ExerciseService service) {
        this.service = service;
    }

    @PostMapping
    public Exercise create(@Valid @RequestBody CreateExerciseRq rq) {
        return service.create(rq);
    }

    @GetMapping
    public List<Exercise> list() {
        return service.list();
    }
}
