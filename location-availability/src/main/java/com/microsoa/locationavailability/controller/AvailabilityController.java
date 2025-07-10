package com.microsoa.locationavailability.controller;

import com.microsoa.locationavailability.model.AvailabilityResponse;
import com.microsoa.locationavailability.model.LocationRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/availability")
@Tag(name = "Location Availability", description = "Endpoints for checking location availability")
public class AvailabilityController {

    @Operation(
        summary = "Check location availability",
        description = "Returns availability status for a specific location and date",
        responses = {
            @ApiResponse(responseCode = "200", description = "Successfully checked availability"),
            @ApiResponse(responseCode = "400", description = "Invalid input parameters")
        }
    )
    @PostMapping
    public ResponseEntity<AvailabilityResponse> checkAvailability(
            @RequestBody LocationRequest request) {
        
        String status;
        String message;
        
        if (request.getLocation().equalsIgnoreCase("Sulmona") && 
            request.getDate().getMonthValue() == 5) {
            status = "Limited";
            message = "High season - limited availability for Sulmona in May";
        } else if (request.getLocation().equalsIgnoreCase("Scanno") && 
                  request.getDate().getDayOfWeek().getValue() > 5) {
            status = "WeekendFull";
            message = "Weekends in Scanno are fully booked";
        } else {
            status = "Available";
            message = "Location available for booking";
        }
        
        return ResponseEntity.ok(new AvailabilityResponse(status, message));
    }
}
