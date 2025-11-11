package com.warlogistics.dto;

public class LogisticsResponse {
    private String origin;
    private String destination;
    private String routeDescription;
    private double safetyScore;
    private int estimatedTime;

    public LogisticsResponse() {}

    public LogisticsResponse(String origin, String destination, String routeDescription, double safetyScore, int estimatedTime) {
        this.origin = origin;
        this.destination = destination;
        this.routeDescription = routeDescription;
        this.safetyScore = safetyScore;
        this.estimatedTime = estimatedTime;
    }

    // Getters and setters
    public String getOrigin() { return origin; }
    public void setOrigin(String origin) { this.origin = origin; }

    public String getDestination() { return destination; }
    public void setDestination(String destination) { this.destination = destination; }

    public String getRouteDescription() { return routeDescription; }
    public void setRouteDescription(String routeDescription) { this.routeDescription = routeDescription; }

    public double getSafetyScore() { return safetyScore; }
    public void setSafetyScore(double safetyScore) { this.safetyScore = safetyScore; }

    public int getEstimatedTime() { return estimatedTime; }
    public void setEstimatedTime(int estimatedTime) { this.estimatedTime = estimatedTime; }
}
