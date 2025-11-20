package com.example.liftloglite.workout;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface WorkoutJpaRepository extends JpaRepository<WorkoutEntity, UUID> {
}