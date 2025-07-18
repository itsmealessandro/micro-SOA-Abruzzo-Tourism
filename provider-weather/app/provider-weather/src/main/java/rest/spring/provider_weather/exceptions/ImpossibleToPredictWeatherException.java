package rest.spring.provider_weather.exceptions;

public class ImpossibleToPredictWeatherException extends Exception {
    public ImpossibleToPredictWeatherException(String message) {
        super(message);
    }
}