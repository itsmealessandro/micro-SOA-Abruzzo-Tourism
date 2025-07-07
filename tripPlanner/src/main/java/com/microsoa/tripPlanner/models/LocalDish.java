package com.microsoa.tripPlanner.models;

public class LocalDish {
    private String name;
    private String description;
    private String region;

    public LocalDish() {
    }

    public LocalDish(String name, String description, String region) {
        this.name = name;
        this.description = description;
        this.region = region;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    @Override
    public String toString() {
        return "LocalDish{name='" + name + "', description='" + description + "', region='" + region + "'}";
    }
}
