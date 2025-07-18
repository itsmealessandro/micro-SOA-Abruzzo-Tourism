
package com.example.weather.client;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Classe Java per weather complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="weather">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="conditions" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="date" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="humidity" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *         &lt;element name="location" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="recommendation" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="suitableForTrails" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="temperature" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *         &lt;element name="windSpeed" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "weather", propOrder = {
    "conditions",
    "date",
    "humidity",
    "location",
    "recommendation",
    "suitableForTrails",
    "temperature",
    "windSpeed"
})
public class Weather {

    protected String conditions;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar date;
    protected double humidity;
    protected String location;
    protected String recommendation;
    protected boolean suitableForTrails;
    protected double temperature;
    protected double windSpeed;

    /**
     * Recupera il valore della proprietà conditions.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getConditions() {
        return conditions;
    }

    /**
     * Imposta il valore della proprietà conditions.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setConditions(String value) {
        this.conditions = value;
    }

    /**
     * Recupera il valore della proprietà date.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDate() {
        return date;
    }

    /**
     * Imposta il valore della proprietà date.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDate(XMLGregorianCalendar value) {
        this.date = value;
    }

    /**
     * Recupera il valore della proprietà humidity.
     * 
     */
    public double getHumidity() {
        return humidity;
    }

    /**
     * Imposta il valore della proprietà humidity.
     * 
     */
    public void setHumidity(double value) {
        this.humidity = value;
    }

    /**
     * Recupera il valore della proprietà location.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLocation() {
        return location;
    }

    /**
     * Imposta il valore della proprietà location.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLocation(String value) {
        this.location = value;
    }

    /**
     * Recupera il valore della proprietà recommendation.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRecommendation() {
        return recommendation;
    }

    /**
     * Imposta il valore della proprietà recommendation.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRecommendation(String value) {
        this.recommendation = value;
    }

    /**
     * Recupera il valore della proprietà suitableForTrails.
     * 
     */
    public boolean isSuitableForTrails() {
        return suitableForTrails;
    }

    /**
     * Imposta il valore della proprietà suitableForTrails.
     * 
     */
    public void setSuitableForTrails(boolean value) {
        this.suitableForTrails = value;
    }

    /**
     * Recupera il valore della proprietà temperature.
     * 
     */
    public double getTemperature() {
        return temperature;
    }

    /**
     * Imposta il valore della proprietà temperature.
     * 
     */
    public void setTemperature(double value) {
        this.temperature = value;
    }

    /**
     * Recupera il valore della proprietà windSpeed.
     * 
     */
    public double getWindSpeed() {
        return windSpeed;
    }

    /**
     * Imposta il valore della proprietà windSpeed.
     * 
     */
    public void setWindSpeed(double value) {
        this.windSpeed = value;
    }

}
