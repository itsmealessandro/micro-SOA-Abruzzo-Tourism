package com.example.locationavailability.model;

import lombok.Data;
import java.util.List;

@Data
public class TrailAvailability {
    private String id;
    private String name;
    private String location;
    private double length;
    private int difficulty;
    private List<String> features;
    private boolean available;
}