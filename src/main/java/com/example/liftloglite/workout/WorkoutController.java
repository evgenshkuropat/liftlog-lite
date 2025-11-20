package com.example.liftloglite.workout;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/workouts")
public class WorkoutController {

    private final WorkoutService service;

    public WorkoutController(WorkoutService service) {
        this.service = service;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Workout createWorkout(@RequestBody CreateWorkoutRequest request) {
        return service.createWorkout(request.getStartedAt());
    }

    @GetMapping
    public List<Workout> getAll() {
        return service.findAll();
    }

    @PostMapping("/set")
    @ResponseStatus(HttpStatus.CREATED)
    public Workout addSet(@RequestBody AddSetRequest request) {
        return service.addSetToWorkout(request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteWorkout(@PathVariable("id") UUID workoutId) {
        service.deleteWorkout(workoutId);
    }

    @PostMapping("/{id}/finish")
    public Workout finishWorkout(@PathVariable("id") UUID workoutId) {
        return service.finishWorkout(workoutId, Instant.now());
    }
}