package com.example.liftloglite.exercise;

import com.example.liftloglite.dto.CreateExerciseRq;
import com.example.liftloglite.dto.ExerciseResponse;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import org.springdoc.core.annotations.ParameterObject;

import java.util.UUID;

@Tag(name = "Exercises", description = "Exercise catalog management")
@RestController
@RequestMapping("/api/exercises")
public class ExerciseController {

    private final ExerciseService service;

    public ExerciseController(ExerciseService service) {
        this.service = service;
    }

    @Operation(
            summary = "Create exercise",
            description = "Creates a new exercise in the catalog"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Exercise created"),
            @ApiResponse(responseCode = "400", description = "Validation error")
    })
    @RequestBody(
            required = true,
            content = @Content(
                    examples = @ExampleObject(
                            name = "Create exercise example",
                            value = """
                            {
                              "name": "Bench Press"
                            }
                            """
                    )
            )
    )
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ExerciseResponse create(@Valid @org.springframework.web.bind.annotation.RequestBody CreateExerciseRq rq) {
        return ExerciseMapper.toResponse(service.create(rq));
    }

    @Operation(
            summary = "Get all exercises (paged)",
            description = "Returns paginated list of exercises"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Page of exercises returned")
    })
    @GetMapping
    public org.springframework.data.domain.Page<ExerciseResponse> list(
            @ParameterObject
            @org.springframework.data.web.PageableDefault(size = 20, sort = "name")
            org.springframework.data.domain.Pageable pageable
    ) {
        return service.list(pageable).map(ExerciseMapper::toResponse);
    }


    @Operation(
            summary = "Delete exercise",
            description = "Deletes exercise by ID"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Exercise deleted"),
            @ApiResponse(responseCode = "404", description = "Exercise not found")
    })
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable UUID id) {
        service.deleteExercise(id);
    }
}