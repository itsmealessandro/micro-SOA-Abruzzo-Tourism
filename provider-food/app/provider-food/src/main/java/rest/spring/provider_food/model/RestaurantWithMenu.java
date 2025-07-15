package rest.spring.provider_food.model;

import java.util.List;

public class RestaurantWithMenu {
    private String id;
    private String name;
    private String location;
    private double rating;
    private List<String> specialties; // These are usually the broad specialties, not the full menu
    private CuisineType cuisineType;
    private List<LocalDish> menu; // The detailed menu items

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

    // Constructor used by FoodService (simplified for initial transformation)
    // You might want to update this or have the service set other fields separately
    public RestaurantWithMenu(String name, String location, List<LocalDish> menu) {
        this.name = name;
        this.location = location;
        this.menu = menu;
        // Defaulting other fields if not provided in the simpler constructor
        this.id = null; // Or generate a UUID
        this.rating = 0.0;
        this.specialties = List.of();
        this.cuisineType = null;
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