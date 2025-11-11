package com.warlogistics.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Logistics {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String origin;
    private String destination;
    private int priority;
    private int weight;
    private int volume;
    private int timeConstraint;
    private double safetyFactor;

    public Logistics() {}

    public Logistics(String origin, String destination, int priority, int weight, int volume, int timeConstraint, double safetyFactor) {
        this.origin = origin;
        this.destination = destination;
        this.priority = priority;
        this.weight = weight;
        this.volume = volume;
        this.timeConstraint = timeConstraint;
        this.safetyFactor = safetyFactor;
    }

    // Getters and setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getOrigin() { return origin; }
    public void setOrigin(String origin) { this.origin = origin; }

    public String getDestination() { return destination; }
    public void setDestination(String destination) { this.destination = destination; }

    public int getPriority() { return priority; }
    public void setPriority(int priority) { this.priority = priority; }

    public int getWeight() { return weight; }
    public void setWeight(int weight) { this.weight = weight; }

    public int getVolume() { return volume; }
    public void setVolume(int volume) { this.volume = volume; }

    public int getTimeConstraint() { return timeConstraint; }
    public void setTimeConstraint(int timeConstraint) { this.timeConstraint = timeConstraint; }

    public double getSafetyFactor() { return safetyFactor; }
    public void setSafetyFactor(double safetyFactor) { this.safetyFactor = safetyFactor; }
}
