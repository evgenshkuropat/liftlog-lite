package com.example.liftloglite.workout;

import org.springframework.data.jpa.repository.JpaRepository;

public interface WorkoutSetJpaRepository extends JpaRepository<WorkoutSetEntity, Long> {
}
