package com.example.liftloglite.workout;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "workout_sets")
public class WorkoutSetEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "workout_id", nullable = false)
    private WorkoutEntity workout;

    @Column(name = "exercise_id", nullable = false)
    private UUID exerciseId;

    @Column(name = "set_no", nullable = false)
    private int setNo;

    @Column(name = "weight_kg", nullable = false)
    private double weightKg;

    @Column(name = "reps", nullable = false)
    private int reps;

    protected WorkoutSetEntity() {
    }

    public WorkoutSetEntity(UUID exerciseId, int setNo, double weightKg, int reps) {
        this.exerciseId = exerciseId;
        this.setNo = setNo;
        this.weightKg = weightKg;
        this.reps = reps;
    }

    public Long getId() {
        return id;
    }

    public WorkoutEntity getWorkout() {
        return workout;
    }

    public void setWorkout(WorkoutEntity workout) {
        this.workout = workout;
    }

    public UUID getExerciseId() {
        return exerciseId;
    }

    public void setExerciseId(UUID exerciseId) {
        this.exerciseId = exerciseId;
    }

    public int getSetNo() {
        return setNo;
    }

    public void setSetNo(int setNo) {
        this.setNo = setNo;
    }

    public double getWeightKg() {
        return weightKg;
    }

    public void setWeightKg(double weightKg) {
        this.weightKg = weightKg;
    }

    public int getReps() {
        return reps;
    }

    public void setReps(int reps) {
        this.reps = reps;
    }
}