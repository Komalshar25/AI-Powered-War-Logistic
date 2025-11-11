package com.warlogistics.model;

public class Location {
    private String name;
    private double lat;
    private double lng;
    private String role;

    public Location(String name, double lat, double lng, String role) {
        this.name = name;
        this.lat = lat;
        this.lng = lng;
        this.role = role;
    }

    public String getName() {
        return name;
    }

    public double getLat() {
        return lat;
    }

    public double getLng() {
        return lng;
    }

    public String getRole() {
        return role;
    }
}
