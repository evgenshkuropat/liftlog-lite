package com.example.liftloglite.workout;

import com.example.liftloglite.dto.AddSetRq;
import com.example.liftloglite.dto.CreateWorkoutRq;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/workouts")
public class WorkoutController {
    private final WorkoutService service;

    public WorkoutController(WorkoutService service) {
        this.service = service;
    }

    @PostMapping
    public Workout create(@Valid @RequestBody CreateWorkoutRq rq) {
        return service.create(rq);
    }

    @PostMapping("/set")
    public Workout addSet(@Valid @RequestBody AddSetRq rq) {
        return service.addSet(rq);
    }

    @GetMapping
    public List<Workout> list() {
        return service.list();
    }
}
