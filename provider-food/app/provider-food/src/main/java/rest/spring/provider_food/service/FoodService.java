package rest.spring.provider_food.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;
import rest.spring.provider_food.model.RestaurantWithMenu;
import rest.spring.provider_food.model.Restaurant;
import rest.spring.provider_food.model.LocalDish; // Import LocalDish

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class FoodService {

    private List<Restaurant> restaurants;

    public FoodService() {
        loadRestaurantsFromJson();
    }

    private void loadRestaurantsFromJson() {
        ObjectMapper mapper = new ObjectMapper();
        try (InputStream is = getClass().getClassLoader().getResourceAsStream("restaurants.json")) {
            if (is != null) {
                restaurants = mapper.readValue(is, new TypeReference<List<Restaurant>>() {});
            } else {
                restaurants = List.of(); // Empty list fallback
                System.err.println("restaurants.json not found in classpath. Initializing with empty list.");
            }
        } catch (IOException e) {
            e.printStackTrace();
            restaurants = List.of();
            System.err.println("Error loading restaurants.json: " + e.getMessage());
        }
    }

    public List<RestaurantWithMenu> getRestaurantsWithMenu(String location) {
        List<Restaurant> filtered = restaurants.stream()
            .filter(r -> r.getLocation().equalsIgnoreCase(location))
            .toList();

        // IMPORTANT: The price and full description for LocalDish are not available in the 'Restaurant' model's 'specialties' list.
        // You'll need to decide how to populate these if they're not in the initial 'restaurants.json'.
        // For now, a placeholder price is used.
        return filtered.stream().map(r -> {
            List<LocalDish> menu = r.getSpecialties().stream()
                // You might need a more sophisticated way to get descriptions and prices
                // For demonstration, a generic description and a placeholder price are used.
                .map(dishName -> new LocalDish(dishName, "Specialità della casa: " + dishName, 15.00)) // Placeholder price
                .toList();

            // When creating RestaurantWithMenu, you'll need to populate id, rating, cuisineType etc.
            // For this example, only name, location, and menu are directly from 'Restaurant'.
            // You might extend your 'Restaurant' model or add a mapping layer to fully populate RestaurantWithMenu.
            return new RestaurantWithMenu(r.getName(), r.getLocation(), menu); // Using the simplified constructor
        }).toList();
    }
}