package com.example.liftloglite.workout;

import com.example.liftloglite.dto.WorkoutResponse;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.Parameter;
import org.springdoc.core.annotations.ParameterObject;
import org.springdoc.core.annotations.ParameterObject;

import java.time.Instant;
import java.util.UUID;

@Tag(name = "Workouts", description = "Workout management")
@RestController
@RequestMapping("/api/workouts")
public class WorkoutController {

    private final WorkoutService service;

    public WorkoutController(WorkoutService service) {
        this.service = service;
    }

    @Operation(
            summary = "Create workout",
            description = "Creates a new workout with start time"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Workout created"),
            @ApiResponse(responseCode = "400", description = "Validation error")
    })
    @RequestBody(
            required = true,
            content = @Content(
                    examples = @ExampleObject(
                            name = "Create workout example",
                            value = "{ \"startedAt\": \"2025-01-01T10:00:00Z\" }"
                    )
            )
    )
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public WorkoutResponse createWorkout(
            @Valid @org.springframework.web.bind.annotation.RequestBody CreateWorkoutRequest request
    ) {
        return WorkoutMapper.toResponse(service.createWorkout(request.getStartedAt()));
    }

    @Operation(
            summary = "Get all workouts (paged)",
            description = "Returns paginated list of workouts"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Page of workouts returned")
    })
    @GetMapping
    public org.springframework.data.domain.Page<WorkoutResponse> getAll(
            @ParameterObject
            @org.springframework.data.web.PageableDefault(size = 10, sort = "startedAt")
            org.springframework.data.domain.Pageable pageable
    ) {
        return service.findAll(pageable).map(WorkoutMapper::toResponse);
    }

    @Operation(
            summary = "Add set to workout",
            description = "Adds a training set to an existing workout"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Set added"),
            @ApiResponse(responseCode = "400", description = "Validation error"),
            @ApiResponse(responseCode = "404", description = "Workout not found")
    })
    @RequestBody(
            required = true,
            content = @Content(
                    examples = @ExampleObject(
                            name = "Add set example",
                            value = """
                            {
                              "exerciseId": "550e8400-e29b-41d4-a716-446655440000",
                              "weightKg": 80,
                              "reps": 8
                            }
                            """
                    )
            )
    )
    @PostMapping("/{id}/sets")
    @ResponseStatus(HttpStatus.CREATED)
    public WorkoutResponse addSet(
            @PathVariable UUID id,
            @Valid @org.springframework.web.bind.annotation.RequestBody AddSetRequest request
    ) {
        return WorkoutMapper.toResponse(service.addSetToWorkout(id, request));
    }

    @Operation(
            summary = "Delete workout",
            description = "Deletes workout by ID"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Workout deleted"),
            @ApiResponse(responseCode = "404", description = "Workout not found")
    })
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteWorkout(@PathVariable UUID workoutId) {
        service.deleteWorkout(workoutId);
    }

    @Operation(
            summary = "Finish workout",
            description = "Sets finishedAt timestamp for a workout"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Workout finished"),
            @ApiResponse(responseCode = "404", description = "Workout not found")
    })
    @PostMapping("/{id}/finish")
    public WorkoutResponse finishWorkout(@PathVariable UUID workoutId) {
        return WorkoutMapper.toResponse(
                service.finishWorkout(workoutId, Instant.now())
        );
    }
}