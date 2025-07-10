package com.microsoa.locationavailability.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;

@Schema(description = "Request object for location availability check")
public class LocationRequest {
    
    @NotBlank(message = "Location cannot be blank")
    @Schema(description = "Name of the location in Abruzzo", example = "Sulmona")
    private String location;
    
    @NotNull(message = "Date cannot be null")
    @Schema(description = "Requested date for availability check", example = "2025-05-15")
    private LocalDate date;

    public LocationRequest() {}

    public LocationRequest(String location, LocalDate date) {
        this.location = location;
        this.date = date;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
}
