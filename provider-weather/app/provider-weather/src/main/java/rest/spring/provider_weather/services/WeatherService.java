package rest.spring.provider_weather.services;

import java.util.Date;

import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import jakarta.jws.WebService;
import rest.spring.provider_weather.exceptions.ImpossibleToPredictWeatherException;
import rest.spring.provider_weather.exceptions.LocationNotFoundException;
import rest.spring.provider_weather.model.Weather;

@WebService
public interface WeatherService {
    @WebMethod
    Weather getWeatherForecast(
        @WebParam(name = "location") String location,
        @WebParam(name = "date") Date date
    ) throws LocationNotFoundException, ImpossibleToPredictWeatherException;
}