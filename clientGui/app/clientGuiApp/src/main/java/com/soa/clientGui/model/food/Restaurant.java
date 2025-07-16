package com.soa.clientGui.model.food;

import java.util.List;

public class Restaurant {
  private Long id;
  private String name;
  private String location;
  private double rating;
  private List<String> specialties;
  private String cuisineType;
  private List<MenuItem> menu;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getLocation() {
    return location;
  }

  public void setLocation(String location) {
    this.location = location;
  }

  public double getRating() {
    return rating;
  }

  public void setRating(double rating) {
    this.rating = rating;
  }

  public List<String> getSpecialties() {
    return specialties;
  }

  public void setSpecialties(List<String> specialties) {
    this.specialties = specialties;
  }

  public String getCuisineType() {
    return cuisineType;
  }

  public void setCuisineType(String cuisineType) {
    this.cuisineType = cuisineType;
  }

  public List<MenuItem> getMenu() {
    return menu;
  }

  public void setMenu(List<MenuItem> menu) {
    this.menu = menu;
  }

  @Override
  public String toString() {
    return "Restaurant [name=" + name + ", location=" + location + ", rating=" + rating + ", specialties=" + specialties
        + ", cuisineType=" + cuisineType + ", menu=" + menu + ", getId()=" + getId() + ", getName()=" + getName()
        + ", getLocation()=" + getLocation() + ", getRating()=" + getRating() + ", getSpecialties()=" + getSpecialties()
        + ", getCuisineType()=" + getCuisineType() + ", getMenu()=" + getMenu() + "]";
  }

}
