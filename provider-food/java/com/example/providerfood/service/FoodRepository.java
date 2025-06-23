package com.example.providerfood.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FoodRepository extends JpaRepository<Restaurant, String> {
    List<Restaurant> findByLocation(String location);
    List<Restaurant> findByLocationAndCuisineType(String location, String cuisineType);
}