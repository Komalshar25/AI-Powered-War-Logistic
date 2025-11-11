package com.warlogistics.dto;

public class OptimizationRequest {
    private String startLocation;
    private String endLocation;
    private int troops;
    private int vehicles;
    private int foodSupply;
    private int weaponsSupply;
    private double roadCondition;

    public OptimizationRequest() {}

    public OptimizationRequest(String startLocation, String endLocation, int troops, int vehicles, int foodSupply, int weaponsSupply, double roadCondition) {
        this.startLocation = startLocation;
        this.endLocation = endLocation;
        this.troops = troops;
        this.vehicles = vehicles;
        this.foodSupply = foodSupply;
        this.weaponsSupply = weaponsSupply;
        this.roadCondition = roadCondition;
    }

    // Getters and setters
    public String getStartLocation() { return startLocation; }
    public void setStartLocation(String startLocation) { this.startLocation = startLocation; }

    public String getEndLocation() { return endLocation; }
    public void setEndLocation(String endLocation) { this.endLocation = endLocation; }

    public int getTroops() { return troops; }
    public void setTroops(int troops) { this.troops = troops; }

    public int getVehicles() { return vehicles; }
    public void setVehicles(int vehicles) { this.vehicles = vehicles; }

    public int getFoodSupply() { return foodSupply; }
    public void setFoodSupply(int foodSupply) { this.foodSupply = foodSupply; }

    public int getWeaponsSupply() { return weaponsSupply; }
    public void setWeaponsSupply(int weaponsSupply) { this.weaponsSupply = weaponsSupply; }

    public double getRoadCondition() { return roadCondition; }
    public void setRoadCondition(double roadCondition) { this.roadCondition = roadCondition; }
}
