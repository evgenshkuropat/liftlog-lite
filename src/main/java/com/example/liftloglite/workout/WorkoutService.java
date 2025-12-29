package com.example.liftloglite.workout;

import com.example.liftloglite.common.NotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class WorkoutService {

    private final WorkoutJpaRepository workoutRepo;
    private final WorkoutSetJpaRepository setRepo;

    public WorkoutService(WorkoutJpaRepository workoutRepo,
                          WorkoutSetJpaRepository setRepo) {
        this.workoutRepo = workoutRepo;
        this.setRepo = setRepo;
    }

    @Transactional
    public Workout createWorkout(Instant startedAt) {
        UUID id = UUID.randomUUID();
        WorkoutEntity entity = new WorkoutEntity(id, startedAt, null);
        WorkoutEntity saved = workoutRepo.save(entity);
        return toDomain(saved);
    }

    @Transactional(readOnly = true)
    public org.springframework.data.domain.Page<Workout> findAll(org.springframework.data.domain.Pageable pageable) {
        return workoutRepo.findAll(pageable).map(this::toDomain);
    }

    @Transactional(readOnly = true)
    public List<Workout> findAll() {
        return workoutRepo.findAll()
                .stream()
                .map(this::toDomain)
                .toList();
    }

    @Transactional
    public Workout addSetToWorkout(UUID workoutId, AddSetRequest req) {
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
        return toDomain(saved);
    }

    @Transactional
    public void deleteWorkout(UUID id) {
        if (!workoutRepo.existsById(id)) {
            throw new NotFoundException("Workout not found: " + id);
        }
        workoutRepo.deleteById(id);
    }

    @Transactional
    public Workout finishWorkout(UUID id, Instant finishedAt) {
        WorkoutEntity workout = workoutRepo.findById(id)
                .orElseThrow(() -> new NotFoundException("Workout not found: " + id));

        workout.setFinishedAt(finishedAt);
        WorkoutEntity saved = workoutRepo.save(workout);
        return toDomain(saved);
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