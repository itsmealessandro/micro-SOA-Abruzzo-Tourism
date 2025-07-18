package com.example.locationavailability.service;

import com.example.locationavailability.model.WeatherInfo;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.ws.client.core.WebServiceTemplate;

import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import java.io.StringReader;
import java.io.StringWriter;

@Service
public class WeatherServiceClient {

  private final WebServiceTemplate webServiceTemplate;

  @Value("${weather.service.url}")
  private String weatherServiceUrl;

  public WeatherServiceClient(WebServiceTemplate webServiceTemplate) {
    this.webServiceTemplate = webServiceTemplate;
  }

  public WeatherInfo getWeatherForecast(String location, String date) {
    System.out.println("##############################################");
    System.out.println("SENDING REQUEST TO WEATHER");
    String request = """
        <weat:getWeatherForecast xmlns:weat="http://services.provider_weather.spring.rest/">
            <location>%s</location>
            <date>%s</date>
        </weat:getWeatherForecast>
        """.formatted(location, date);

    System.out.println(request);
    System.out.println("##############################################");

    System.out.println("URL IS:");
    System.out.println(weatherServiceUrl);

    StringWriter writer = new StringWriter();
    webServiceTemplate.sendSourceAndReceiveToResult(
        weatherServiceUrl,
        new StreamSource(new StringReader(request)),
        new StreamResult(writer));

    String response = writer.toString();
    // Parsing semplificato (in produzione usare JAXB)
    return parseWeatherResponse(response);
  }

  private WeatherInfo parseWeatherResponse(String xml) {
    WeatherInfo info = new WeatherInfo();
    // Estrai valori dall'XML (esempio semplificato)
    info.setLocation(extractTagValue(xml, "location"));
    info.setConditions(extractTagValue(xml, "conditions"));
    info.setTemperature(Double.parseDouble(extractTagValue(xml, "temperature")));
    info.setSuitableForTrails(Boolean.parseBoolean(extractTagValue(xml, "suitableForTrails")));
    return info;
  }

  private String extractTagValue(String xml, String tag) {
    int start = xml.indexOf("<" + tag + ">") + tag.length() + 2;
    int end = xml.indexOf("</" + tag + ">");
    return xml.substring(start, end);
  }
}
