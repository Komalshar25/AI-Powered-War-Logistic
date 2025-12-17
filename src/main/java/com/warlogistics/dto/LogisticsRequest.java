package com.warlogistics.dto;

public class LogisticsRequest {
    private String origin;
    private String destination;

    public String getOrigin() { return origin; }
    public void setOrigin(String origin) { this.origin = origin; }
    public String getDestination() { return destination; }
    public void setDestination(String destination) { this.destination = destination; }
}
package com.warlogistics.dto;

public class LogisticsRequest {
    private String origin;
    private String destination;
    private String cargoType;
    private int quantity;
    private String priority;
    private String threatLevel;

    public LogisticsRequest() {}

    public LogisticsRequest(String origin, String destination, String cargoType, int quantity, String priority, String threatLevel) {
        this.origin = origin;
        this.destination = destination;
        this.cargoType = cargoType;
        this.quantity = quantity;
        this.priority = priority;
        this.threatLevel = threatLevel;
    }

    // Getters and setters
    public String getOrigin() { return origin; }
    public void setOrigin(String origin) { this.origin = origin; }

    public String getDestination() { return destination; }
    public void setDestination(String destination) { this.destination = destination; }

    public String getCargoType() { return cargoType; }
    public void setCargoType(String cargoType) { this.cargoType = cargoType; }

    public int getQuantity() { return quantity; }
    public void setQuantity(int quantity) { this.quantity = quantity; }

    public String getPriority() { return priority; }
    public void setPriority(String priority) { this.priority = priority; }

    public String getThreatLevel() { return threatLevel; }
    public void setThreatLevel(String threatLevel) { this.threatLevel = threatLevel; }
}
