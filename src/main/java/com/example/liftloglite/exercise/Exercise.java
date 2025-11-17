package com.example.liftloglite.exercise;

import java.util.UUID;

public class Exercise {
    private final UUID id;
    private final String name;
    private final String muscle;
    private final String equipment;

    public Exercise(UUID id, String name, String muscle, String equipment) {
        this.id = id;
        this.name = name;
        this.muscle = muscle;
        this.equipment = equipment;
    }

    public UUID getId() { return id; }
    public String getName() { return name; }
    public String getMuscle() { return muscle; }
    public String getEquipment() { return equipment; }
}
