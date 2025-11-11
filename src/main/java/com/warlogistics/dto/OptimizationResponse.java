package com.warlogistics.dto;

import java.util.List;

public class OptimizationResponse {
    private List<String> path;
    private double totalCost;
    private double safetyScore;
    private int estimatedTime;
    private String analysis;
    private String startLocation;
    private String endLocation;

    public OptimizationResponse() {}

    public OptimizationResponse(List<String> path, double totalCost, double safetyScore, int estimatedTime, String analysis, String startLocation, String endLocation) {
        this.path = path;
        this.totalCost = totalCost;
        this.safetyScore = safetyScore;
        this.estimatedTime = estimatedTime;
        this.analysis = analysis;
        this.startLocation = startLocation;
        this.endLocation = endLocation;
    }

    // Getters and setters
    public List<String> getPath() { return path; }
    public void setPath(List<String> path) { this.path = path; }

    public double getTotalCost() { return totalCost; }
    public void setTotalCost(double totalCost) { this.totalCost = totalCost; }

    public double getSafetyScore() { return safetyScore; }
    public void setSafetyScore(double safetyScore) { this.safetyScore = safetyScore; }

    public int getEstimatedTime() { return estimatedTime; }
    public void setEstimatedTime(int estimatedTime) { this.estimatedTime = estimatedTime; }

    public String getAnalysis() { return analysis; }
    public void setAnalysis(String analysis) { this.analysis = analysis; }

    public String getStartLocation() { return startLocation; }
    public void setStartLocation(String startLocation) { this.startLocation = startLocation; }

    public String getEndLocation() { return endLocation; }
    public void setEndLocation(String endLocation) { this.endLocation = endLocation; }
}
