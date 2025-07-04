package rest.spring.provider_food.model;

import jakarta.persistence.*;
import java.util.List;

@Entity
public class Restaurant {
    @Id
    private String id;
    private String name;
    private String location;
    private double rating;
    
    @ElementCollection
    private List<String> specialties;
    
    @Enumerated(EnumType.STRING)
    private CuisineType cuisineType;

    // Aggiungi un costruttore vuoto
    public Restaurant() {}

    // Costruttore con parametri
    public Restaurant(String id, String name, String location, double rating, 
                     List<String> specialties, CuisineType cuisineType) {
        this.id = id;
        this.name = name;
        this.location = location;
        this.rating = rating;
        this.specialties = specialties;
        this.cuisineType = cuisineType;
    }

    // Getters and setters...
}