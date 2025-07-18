package com.example.weather;

public class WeatherClientTest {

    public static void main(String[] args) {
        // Istanzia il service generato da wsimport
        WeatherService service = new WeatherService();

        // Ottieni la porta (interfaccia)
        WeatherPortType port = service.getWeatherPort();

        // Crea la richiesta
        GetWeatherRequest request = new GetWeatherRequest();
        request.setCity("Teramo");

        // Invia la richiesta
        GetWeatherResponse response = port.getWeather(request);

        // Stampa la risposta
        System.out.println("Risposta dal servizio:");
        System.out.println("Descrizione: " + response.getDescription());
        System.out.println("Temperatura: " + response.getTemperature());
    }
}
