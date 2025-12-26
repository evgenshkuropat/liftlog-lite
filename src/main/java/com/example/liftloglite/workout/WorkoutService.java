package com.example.liftloglite.workout;

import com.example.liftloglite.common.exception.NotFoundException;
import com.example.liftloglite.workout.dto.AddSetRequest;
import com.example.liftloglite.workout.dto.CreateWorkoutRequest;
import com.example.liftloglite.workout.dto.WorkoutResponse;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class WorkoutService {

    private final WorkoutJpaRepository workoutRepo;

    public WorkoutService(WorkoutJpaRepository workoutRepo) {
        this.workoutRepo = workoutRepo;
    }

    @Transactional
    public WorkoutResponse createWorkout(CreateWorkoutRequest request) {
        UUID id = UUID.randomUUID();
        WorkoutEntity entity = new WorkoutEntity(id, request.getStartedAt(), null);
        WorkoutEntity saved = workoutRepo.save(entity);
        return WorkoutMapper.toResponse(toDomain(saved));
    }

    @Transactional(readOnly = true)
    public List<WorkoutResponse> findAll() {
        return workoutRepo.findAll()
                .stream()
                .map(this::toDomain)
                .map(WorkoutMapper::toResponse)
                .toList();
    }

    @Transactional
    public WorkoutResponse addSetToWorkout(UUID workoutId, AddSetRequest req) {
        WorkoutEntity workout = workoutRepo.findById(workoutId)
                .orElseThrow(() -> new NotFoundException("Workout not found: " + workoutId));

        int nextSetNo = workout.getSets().stream()
                .filter(s -> s.getExerciseId().equals(req.getExerciseId()))
                .mapToInt(WorkoutSetEntity::getSetNo)
                .max()
                .orElse(0) + 1;

        WorkoutSetEntity setEntity = new WorkoutSetEntity(
                req.getExerciseId(),
                nextSetNo,
                req.getWeightKg(),
                req.getReps()
        );
        workout.addSet(setEntity);

        WorkoutEntity saved = workoutRepo.save(workout);
        return WorkoutMapper.toResponse(toDomain(saved));
    }

    @Transactional
    public void deleteWorkout(UUID id) {
        if (!workoutRepo.existsById(id)) {
            throw new NotFoundException("Workout not found: " + id);
        }
        workoutRepo.deleteById(id);
    }

    @Transactional
    public WorkoutResponse finishWorkout(UUID id) {
        WorkoutEntity workout = workoutRepo.findById(id)
                .orElseThrow(() -> new NotFoundException("Workout not found: " + id));

        workout.setFinishedAt(Instant.now());
        WorkoutEntity saved = workoutRepo.save(workout);
        return WorkoutMapper.toResponse(toDomain(saved));
    }

    // === mapping ===
    private Workout toDomain(WorkoutEntity entity) {
        var grouped = entity.getSets().stream()
                .collect(Collectors.groupingBy(
                        WorkoutSetEntity::getExerciseId,
                        Collectors.mapping(
                                s -> new WorkoutSet(s.getSetNo(), s.getWeightKg(), s.getReps()),
                                Collectors.toList()
                        )
                ));

        return new Workout(
                entity.getId(),
                entity.getStartedAt(),
                entity.getFinishedAt(),
                grouped
        );
    }
}
