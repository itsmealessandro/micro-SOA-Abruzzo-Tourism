package com.example.locationavailability.service;

import com.example.locationavailability.model.Weather;
import com.example.locationavailability.model.WeatherCondition;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.ws.client.core.WebServiceTemplate;

import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import java.io.StringReader;
import java.io.StringWriter;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Date;
import java.util.Locale;

@Service
public class WeatherServiceClient {

  private final WebServiceTemplate webServiceTemplate;

  @Value("${weather.service.url}")
  private String weatherServiceUrl;

  public WeatherServiceClient(WebServiceTemplate webServiceTemplate) {
    this.webServiceTemplate = webServiceTemplate;
  }

  public Weather getWeatherForecast(String location, String date) {
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

  public Weather parseWeatherResponse(String xml) {

    System.out.println("############################################");
    System.out.println("XML ");
    System.out.println(xml);
    System.out.println("############################################");
    Weather info = new Weather();

    info.setLocation(extractTagValue(xml, "location"));
    try {
      String dateStr = extractTagValue(xml, "date");
      ZonedDateTime zonedDateTime = ZonedDateTime.parse(dateStr);
      info.setDate(Date.from(zonedDateTime.toInstant()));
    } catch (DateTimeParseException e) {
      throw new RuntimeException("Invalid date format in weather response", e);
    }
    info.setWeatherCondition(WeatherCondition.valueOf(extractTagValue(xml, "weatherCondition")));
    info.setTemperature(Double.parseDouble(extractTagValue(xml, "temperature")));
    info.setHumidity(Double.parseDouble(extractTagValue(xml, "humidity")));
    info.setWindSpeed(Double.parseDouble(extractTagValue(xml, "windSpeed")));

    System.out.println("#######################################");
    System.out.println("RESPONSE FROM WEATHER API");
    System.out.println(info.toString());
    System.out.println("#######################################");

    return info;
  }

  private String extractTagValue(String xml, String tag) {
    String openTag = "<" + tag + ">";
    String closeTag = "</" + tag + ">";

    int start = xml.indexOf(openTag);
    int end = xml.indexOf(closeTag);

    if (start == -1 || end == -1 || start + openTag.length() > end) {
      throw new RuntimeException("Missing or malformed tag: <" + tag + ">");
    }

    start += openTag.length();
    return xml.substring(start, end).trim();
  }
}
