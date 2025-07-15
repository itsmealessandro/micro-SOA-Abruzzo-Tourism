package rest.spring.provider_food.controller;

import org.springframework.web.bind.annotation.*;
import rest.spring.provider_food.model.RestaurantWithMenu;
import rest.spring.provider_food.service.FoodService;

import java.util.List;

@RestController
@RequestMapping("/food")
public class FoodController {

    private final FoodService foodService;

    public FoodController(FoodService foodService) {
        this.foodService = foodService;
    }

    @GetMapping("/restaurants-with-menu")
    public List<RestaurantWithMenu> getRestaurantsWithMenu(@RequestParam String location) {
        // CORRECTED: Calling the right method name from FoodService
        return foodService.getRestaurantsWithMenu(location);
    }
}