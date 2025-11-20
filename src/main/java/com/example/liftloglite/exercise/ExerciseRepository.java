package com.example.liftloglite.exercise;

import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public class ExerciseRepository {

    private final ExerciseJpaRepository jpaRepo;

    public ExerciseRepository(ExerciseJpaRepository jpaRepo) {
        this.jpaRepo = jpaRepo;
    }

    public Exercise save(Exercise e) {
        ExerciseEntity entity = toEntity(e);
        ExerciseEntity saved = jpaRepo.save(entity);
        return toDomain(saved);
    }

    public Optional<Exercise> findById(UUID id) {
        return jpaRepo.findById(id).map(this::toDomain);
    }

    public List<Exercise> findAll() {
        return jpaRepo.findAll()
                .stream()
                .map(this::toDomain)
                .toList();
    }

    // === mapping ===

    private ExerciseEntity toEntity(Exercise e) {
        return new ExerciseEntity(e.getId(), e.getName(), e.getMuscle(), e.getEquipment());
    }

    private Exercise toDomain(ExerciseEntity entity) {
        return new Exercise(entity.getId(), entity.getName(), entity.getMuscle(), entity.getEquipment());
    }
}
