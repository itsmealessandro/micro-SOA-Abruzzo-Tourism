package com.example.providerfood.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class LocalDish {
    @Id
    private String id;
    private String name;
    private String description;
    private String region;

    public LocalDish() {}

    public LocalDish(String id, String name, String description, String region) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.region = region;
    }

    // Getters
    public String getId() { return id; }
    public String getName() { return name; }
    public String getDescription() { return description; }
    public String getRegion() { return region; }
    
    // Setters
    public void setId(String id) { this.id = id; }
    public void setName(String name) { this.name = name; }
    public void setDescription(String description) { this.description = description; }
    public void setRegion(String region) { this.region = region; }
}