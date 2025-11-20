package com.example.liftloglite.exercise;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.util.UUID;

@Entity
@Table(name = "exercises")
public class ExerciseEntity {

    @Id
    @Column(name = "id", nullable = false, updatable = false)
    private UUID id;

    @Column(name = "name", nullable = false, length = 200)
    private String name;

    @Column(name = "muscle", nullable = false, length = 100)
    private String muscle;

    @Column(name = "equipment", nullable = false, length = 100)
    private String equipment;

    protected ExerciseEntity() {
        // JPA
    }

    public ExerciseEntity(UUID id, String name, String muscle, String equipment) {
        this.id = id;
        this.name = name;
        this.muscle = muscle;
        this.equipment = equipment;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMuscle() {
        return muscle;
    }

    public void setMuscle(String muscle) {
        this.muscle = muscle;
    }

    public String getEquipment() {
        return equipment;
    }

    public void setEquipment(String equipment) {
        this.equipment = equipment;
    }
}
