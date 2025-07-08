package com.microsoa.tripPlanner.models;

import java.util.List;

// This class should be identical to rest.spring.provider_food.model.RestaurantWithMenu
// if it's consuming the same JSON structure.
public class RestaurantWithMenu {
    private String id;
    private String name;
    private String location;
    private double rating;
    private List<String> specialties;
    private CuisineType cuisineType; // You'll need to define CuisineType in this package too
    private List<LocalDish> menu;

    public RestaurantWithMenu() {}

    public RestaurantWithMenu(String id, String name, String location, double rating, List<String> specialties, CuisineType cuisineType, List<LocalDish> menu) {
        this.id = id;
        this.name = name;
        this.location = location;
        this.rating = rating;
        this.specialties = specialties;
        this.cuisineType = cuisineType;
        this.menu = menu;
    }

    // A simpler constructor, if used for internal mapping
    public RestaurantWithMenu(String name, String location, List<LocalDish> menu) {
        this(null, name, location, 0.0, List.of(), null, menu); // Delegate to full constructor
    }

    // Getters and setters
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getLocation() { return location; }
    public void setLocation(String location) { this.location = location; }

    public double getRating() { return rating; }
    public void setRating(double rating) { this.rating = rating; }

    public List<String> getSpecialties() { return specialties; }
    public void setSpecialties(List<String> specialties) { this.specialties = specialties; }

    public CuisineType getCuisineType() { return cuisineType; }
    public void setCuisineType(CuisineType cuisineType) { this.cuisineType = cuisineType; }

    public List<LocalDish> getMenu() { return menu; }
    public void setMenu(List<LocalDish> menu) { this.menu = menu; }
}