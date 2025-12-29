package com.example.liftloglite.exercise;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ExerciseJpaRepository extends org.springframework.data.jpa.repository.JpaRepository<ExerciseEntity, UUID> {
}