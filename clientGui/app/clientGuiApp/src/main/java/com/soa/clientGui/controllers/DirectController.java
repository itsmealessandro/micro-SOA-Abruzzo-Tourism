package com.soa.clientGui.controllers;

import com.soa.clientGui.model.RestaurantWithMenu;
import com.soa.clientGui.services.FoodService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Collections; // Import per Collections.emptyList()
import java.util.List;

@Controller
@RequestMapping("/direct") // Questo controller gestisce le richieste su /direct/**
public class DirectController {

    private static final Logger logger = LoggerFactory.getLogger(DirectController.class);

    private final FoodService foodService;

    public DirectController(FoodService foodService) {
        this.foodService = foodService;
    }

    // Ora, l'endpoint /direct/food può essere raggiunto senza il parametro 'location'
    @GetMapping("/food")
    public String getDirectFoodInfo(
            @RequestParam(value = "location", required = false) String location, // 'location' ora è opzionale
            Model model) {

        // Aggiungi la località al modello, sia che sia presente o meno (per pre-popolare il form)
        model.addAttribute("requestedLocationForDirectCall", location);

        // Esegui la ricerca solo se la località è presente e non vuota
        if (location != null && !location.trim().isEmpty()) {
            logger.info("Richiesta diretta per informazioni sul cibo (RestaurantWithMenu) per la località: {}", location);

            try {
                List<RestaurantWithMenu> additionalRestaurantInfo = foodService.getRestaurantsByLocation(location);

                model.addAttribute("directCallSuccess", "Informazioni Ristoranti aggiuntive (chiamata diretta):");
                model.addAttribute("additionalRestaurantInfo", additionalRestaurantInfo);

                logger.info("Info ristoranti diretta ottenuta con successo per {}: {}", location, additionalRestaurantInfo);

            } catch (Exception e) {
                logger.error("Errore durante la chiamata diretta a provider-food per {}: {}", location, e.getMessage(), e);
                model.addAttribute("directCallError", "Errore nel recuperare informazioni ristoranti aggiuntive direttamente.");
                // Anche in caso di errore, potresti voler mostrare una lista vuota o null per non far esplodere la vista
                model.addAttribute("additionalRestaurantInfo", Collections.emptyList()); 
            }
        } else {
            // Se non c'è una location, non ci sono risultati da mostrare inizialmente
            model.addAttribute("directCallSuccess", "Inserisci una località per cercare ristoranti.");
            model.addAttribute("additionalRestaurantInfo", Collections.emptyList()); // Inizializza a vuoto
        }

        // Restituisce sempre la stessa pagina, che gestirà la visualizzazione condizionale
        return "direct_result_page";
    }
}