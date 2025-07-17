package rest.spring.provider_trails.model;

public class WeatherAdaptability {
    private boolean suitable;
    private String reason;
    private String recommendation;

    public WeatherAdaptability(boolean suitable, String reason, String recommendation) {
        this.suitable = suitable;
        this.reason = reason;
        this.recommendation = recommendation;
    }

    // Getters
    public boolean isSuitable() { return suitable; }
    public String getReason() { return reason; }
    public String getRecommendation() { return recommendation; }
    
    // Setters
    public void setSuitable(boolean suitable) { this.suitable = suitable; }
    public void setReason(String reason) { this.reason = reason; }
    public void setRecommendation(String recommendation) { this.recommendation = recommendation; }
}