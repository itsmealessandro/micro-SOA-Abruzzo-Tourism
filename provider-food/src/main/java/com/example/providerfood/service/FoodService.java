package com.example.providerfood.service;

import com.example.providerfood.model.Restaurant;
import com.example.providerfood.model.LocalDish;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Arrays;
import java.util.List;

@Service
public class FoodService {

    private final FoodRepository foodRepository;

    @Autowired
    public FoodService(FoodRepository foodRepository) {
        this.foodRepository = foodRepository;
    }

    public List<Restaurant> findRestaurants(String location, String cuisineType) {
        if (cuisineType == null || cuisineType.isEmpty()) {
            return foodRepository.findByLocation(location);
        }
        return foodRepository.findByLocationAndCuisineType(location, cuisineType);
    }

    public List<LocalDish> findDishesByRegion(String region) {
        return Arrays.asList(
            new LocalDish("d1", "Arrosticini", "Spiedini di carne ovina", "L'Aquila"),
            new LocalDish("d2", "Maccheroni alla chitarra", "Pasta fresca con sugo di agnello", "Chieti"),
            new LocalDish("d3", "Confetti", "Famosi dolci di Sulmona", "Sulmona")
        );
    }
}