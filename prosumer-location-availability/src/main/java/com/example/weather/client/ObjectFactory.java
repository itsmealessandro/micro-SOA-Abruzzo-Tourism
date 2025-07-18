
package com.example.weather.client;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.example.weather.client package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _GetWeatherForecastResponse_QNAME = new QName("http://services.provider_weather.spring.rest/", "getWeatherForecastResponse");
    private final static QName _GetWeatherForecast_QNAME = new QName("http://services.provider_weather.spring.rest/", "getWeatherForecast");
    private final static QName _ImpossibleToPredictWeatherException_QNAME = new QName("http://services.provider_weather.spring.rest/", "ImpossibleToPredictWeatherException");
    private final static QName _LocationNotFoundException_QNAME = new QName("http://services.provider_weather.spring.rest/", "LocationNotFoundException");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.example.weather.client
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link GetWeatherForecast }
     * 
     */
    public GetWeatherForecast createGetWeatherForecast() {
        return new GetWeatherForecast();
    }

    /**
     * Create an instance of {@link ImpossibleToPredictWeatherException }
     * 
     */
    public ImpossibleToPredictWeatherException createImpossibleToPredictWeatherException() {
        return new ImpossibleToPredictWeatherException();
    }

    /**
     * Create an instance of {@link GetWeatherForecastResponse }
     * 
     */
    public GetWeatherForecastResponse createGetWeatherForecastResponse() {
        return new GetWeatherForecastResponse();
    }

    /**
     * Create an instance of {@link LocationNotFoundException }
     * 
     */
    public LocationNotFoundException createLocationNotFoundException() {
        return new LocationNotFoundException();
    }

    /**
     * Create an instance of {@link Weather }
     * 
     */
    public Weather createWeather() {
        return new Weather();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetWeatherForecastResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://services.provider_weather.spring.rest/", name = "getWeatherForecastResponse")
    public JAXBElement<GetWeatherForecastResponse> createGetWeatherForecastResponse(GetWeatherForecastResponse value) {
        return new JAXBElement<GetWeatherForecastResponse>(_GetWeatherForecastResponse_QNAME, GetWeatherForecastResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetWeatherForecast }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://services.provider_weather.spring.rest/", name = "getWeatherForecast")
    public JAXBElement<GetWeatherForecast> createGetWeatherForecast(GetWeatherForecast value) {
        return new JAXBElement<GetWeatherForecast>(_GetWeatherForecast_QNAME, GetWeatherForecast.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ImpossibleToPredictWeatherException }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://services.provider_weather.spring.rest/", name = "ImpossibleToPredictWeatherException")
    public JAXBElement<ImpossibleToPredictWeatherException> createImpossibleToPredictWeatherException(ImpossibleToPredictWeatherException value) {
        return new JAXBElement<ImpossibleToPredictWeatherException>(_ImpossibleToPredictWeatherException_QNAME, ImpossibleToPredictWeatherException.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link LocationNotFoundException }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://services.provider_weather.spring.rest/", name = "LocationNotFoundException")
    public JAXBElement<LocationNotFoundException> createLocationNotFoundException(LocationNotFoundException value) {
        return new JAXBElement<LocationNotFoundException>(_LocationNotFoundException_QNAME, LocationNotFoundException.class, null, value);
    }

}
