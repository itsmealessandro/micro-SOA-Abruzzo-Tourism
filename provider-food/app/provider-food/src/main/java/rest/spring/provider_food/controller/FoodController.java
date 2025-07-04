package rest.spring.provider_food.controller;

import rest.spring.provider_food.model.LocalDish;
import rest.spring.provider_food.model.Restaurant;
import rest.spring.provider_food.service.FoodService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/food")
@Tag(name = "Food API", description = "Local cuisine and restaurants in Abruzzo")
public class FoodController {

    private final FoodService foodService;

    // Usa l'iniezione nel costruttore
    @Autowired
    public FoodController(FoodService foodService) {
        this.foodService = foodService;
    }

    @Operation(summary = "Get restaurants by location and cuisine type")
    @GetMapping("/restaurants")
    public List<Restaurant> getRestaurants(
            @Parameter(description = "Location in Abruzzo", example = "Sulmona")
            @RequestParam String location,
            
            @Parameter(description = "Cuisine type", example = "TRADITIONAL")
            @RequestParam(required = false) String cuisineType) {
        
        return foodService.findRestaurants(location, cuisineType);
    }

    @Operation(summary = "Get local dishes by region")
    @GetMapping("/dishes")
    public List<LocalDish> getLocalDishes(
            @Parameter(description = "Region in Abruzzo", example = "Chieti")
            @RequestParam String region) {
        
        return foodService.findDishesByRegion(region);
    }
}