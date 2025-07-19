package com.soa.clientGui.services;

import com.soa.clientGui.model.RestaurantWithMenu;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.List;

@Service
public class FoodService {

    public static final Logger logger = LoggerFactory.getLogger(FoodService.class);

    public final RestTemplate restTemplate;

    @Value("${direct.food.service.url}")
    private String directFoodServiceUrl;

    public FoodService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public List<RestaurantWithMenu> getRestaurantsByLocation(String location) {
        String url = directFoodServiceUrl.replace("{location}", location);
        logger.info("FoodService (clientGui): Chiamata diretta a provider-food all'URL: {}", url);

        try {
            ResponseEntity<List<RestaurantWithMenu>> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<RestaurantWithMenu>>() {}
            );

            // ******* QUESTO È IL LOG CRUCIALE *******
            String rawJsonResponse = response.getBody() != null ? response.getBody().toString() : "NULL_BODY";
            if (response.hasBody() && !response.getBody().isEmpty()) {
                logger.info("FoodService (clientGui): Ricevuto JSON RAW dal provider-food: {}", rawJsonResponse);
            } else {
                logger.warn("FoodService (clientGui): Ricevuta risposta VUOTA o non JSON valida dal provider-food. Raw: {}", rawJsonResponse);
            }
            // ****************************************

            List<RestaurantWithMenu> restaurants = response.getBody();

            if (restaurants != null && !restaurants.isEmpty()) {
                logger.info("FoodService (clientGui): Deserializzati {} ristoranti. Primo elemento (oggetto Java): {}", restaurants.size(), restaurants.get(0));
            } else {
                logger.info("FoodService (clientGui): Lista di ristoranti deserializzata è vuota o null.");
            }

            return (restaurants != null) ? restaurants : Collections.emptyList();

        } catch (Exception e) {
            logger.error("FoodService (clientGui): Errore durante la richiesta al provider-food o la deserializzazione: {}", e.getMessage(), e);
            throw new RuntimeException("Errore durante il recupero dei dati dai ristoranti.", e);
        }
    }
}