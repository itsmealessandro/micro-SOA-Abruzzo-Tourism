package rest.spring.provider_weather.exceptions;

public class LocationNotFoundException extends Exception {
    public LocationNotFoundException(String message) {
        super(message);
    }
}