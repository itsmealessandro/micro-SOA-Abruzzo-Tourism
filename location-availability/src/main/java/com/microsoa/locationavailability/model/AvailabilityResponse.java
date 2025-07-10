package com.microsoa.locationavailability.model;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Response object for availability check")
public class AvailabilityResponse {
    
    @Schema(description = "Availability status", example = "Available")
    private String status;
    
    @Schema(description = "Detailed message", example = "Location available for booking")
    private String message;

    public AvailabilityResponse(String status, String message) {
        this.status = status;
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }
}
