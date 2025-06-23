package com.example.providerfood.model;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;

@Entity
public class Restaurant {
    @Id
    private String id;
    private String name;
    private String location;
    private double rating;
    private List<String> specialties;
    
    @Enumerated(EnumType.STRING)
    private CuisineType cuisineType;

    public Restaurant() {}

    public Restaurant(String id, String name, String location, double rating, 
                     List<String> specialties, CuisineType cuisineType) {
        this.id = id;
        this.name = name;
        this.location = location;
        this.rating = rating;
        this.specialties = specialties;
        this.cuisineType = cuisineType;
    }

    // Getters
    public String getId() { return id; }
    public String getName() { return name; }
    public String getLocation() { return location; }
    public double getRating() { return rating; }
    public List<String> getSpecialties() { return specialties; }
    public CuisineType getCuisineType() { return cuisineType; }
    
    // Setters
    public void setId(String id) { this.id = id; }
    public void setName(String name) { this.name = name; }
    public void setLocation(String location) { this.location = location; }
    public void setRating(double rating) { this.rating = rating; }
    public void setSpecialties(List<String> specialties) { this.specialties = specialties; }
    public void setCuisineType(CuisineType cuisineType) { this.cuisineType = cuisineType; }
}