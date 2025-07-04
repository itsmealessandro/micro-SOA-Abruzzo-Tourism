package rest.spring.provider_food.service;


import rest.spring.provider_food.model.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface FoodRepository extends JpaRepository<Restaurant, String> {
    List<Restaurant> findByLocation(String location);
    List<Restaurant> findByLocationAndCuisineType(String location, String cuisineType);
}