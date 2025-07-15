package rest.spring.provider_food.model;

import java.util.List;

public class Restaurant {
    private String name;
    private String location;
    private List<String> specialties; // This list usually contains just the names of dishes

    public Restaurant() {}

    public Restaurant(String name, String location, List<String> specialties) {
        this.name = name;
        this.location = location;
        this.specialties = specialties;
    }

    public String getName() {
        return name;
    }

    public String getLocation() {
        return location;
    }

    public List<String> getSpecialties() {
        return specialties;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setSpecialties(List<String> specialties) {
        this.specialties = specialties;
    }
}