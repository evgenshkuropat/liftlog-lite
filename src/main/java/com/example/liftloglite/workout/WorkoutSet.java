package com.example.liftloglite.workout;

public class WorkoutSet {

    private final int setNo;
    private final double weightKg;
    private final int reps;

    public WorkoutSet(int setNo, double weightKg, int reps) {
        this.setNo = setNo;
        this.weightKg = weightKg;
        this.reps = reps;
    }

    public int getSetNo() {
        return setNo;
    }

    public double getWeightKg() {
        return weightKg;
    }

    public int getReps() {
        return reps;
    }
}