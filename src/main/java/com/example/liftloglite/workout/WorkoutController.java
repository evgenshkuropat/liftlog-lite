package com.example.liftloglite.workout;

import com.example.liftloglite.workout.dto.AddSetRequest;
import com.example.liftloglite.workout.dto.CreateWorkoutRequest;
import com.example.liftloglite.workout.dto.WorkoutResponse;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

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
    public WorkoutResponse createWorkout(@Valid @RequestBody CreateWorkoutRequest request) {
        return service.createWorkout(request);
    }

    @GetMapping
    public List<WorkoutResponse> getAll() {
        return service.findAll();
    }

    @PostMapping("/{id}/sets")
    @ResponseStatus(HttpStatus.CREATED)
    public WorkoutResponse addSet(@PathVariable UUID id, @Valid @RequestBody AddSetRequest request) {
        return service.addSetToWorkout(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteWorkout(@PathVariable UUID id) {
        service.deleteWorkout(id);
    }

    @PostMapping("/{id}/finish")
    public WorkoutResponse finishWorkout(@PathVariable UUID id) {
        return service.finishWorkout(id); // сервис сам ставит now()
    }
}
