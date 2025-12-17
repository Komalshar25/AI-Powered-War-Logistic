package com.warlogistics.service;

import com.warlogistics.dto.OptimizationRequest;
import com.warlogistics.dto.OptimizationResponse;
import org.springframework.stereotype.Service;

@Service
public class OptimizationService {
    public OptimizationResponse optimize(OptimizationRequest req) {
        OptimizationResponse res = new OptimizationResponse();
        res.setSummary("No optimization implemented yet for: " + req.getMissionName());
        return res;
    }
}
package com.warlogistics.service;

import com.warlogistics.dto.OptimizationRequest;
import com.warlogistics.dto.OptimizationResponse;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class OptimizationService {

    // Define key Indian logistics locations (Nodes in our Graph)
    private static final Map<String, Location> LOCATIONS = new HashMap<>();
    static {
        LOCATIONS.put("DELHI", new Location("New Delhi (HQ)", 28.6139, 77.2090));
        LOCATIONS.put("MUMBAI", new Location("Mumbai (Logistics Hub)", 19.0760, 72.8777));
        LOCATIONS.put("CHENNAI", new Location("Chennai (Southern Supply)", 13.0827, 80.2707));
        LOCATIONS.put("SRINAGAR", new Location("Srinagar (Forward Base)", 34.0837, 74.7973));
        LOCATIONS.put("LEH", new Location("Leh (High Altitude)", 34.1526, 77.5770));
        LOCATIONS.put("GUWAHATI", new Location("Guwahati (Eastern Sector)", 26.1445, 91.7368));
    }

    // Safety-Weighted Network Graph Definition
    private static final Map<String, Map<String, Double>> GRAPH = new HashMap<>();
    static {
        GRAPH.put("DELHI", Map.of(
            "MUMBAI", calculateSafetyCost("DELHI", "MUMBAI", 10, 1.25),
            "SRINAGAR", calculateSafetyCost("DELHI", "SRINAGAR", 80, 1.5),
            "LEH", calculateSafetyCost("DELHI", "LEH", 90, 1.8),
            "GUWAHATI", calculateSafetyCost("DELHI", "GUWAHATI", 20, 1.3)
        ));
        GRAPH.put("MUMBAI", Map.of(
            "DELHI", calculateSafetyCost("MUMBAI", "DELHI", 10, 1.25),
            "CHENNAI", calculateSafetyCost("MUMBAI", "CHENNAI", 5, 1.15)
        ));
        GRAPH.put("CHENNAI", Map.of(
            "MUMBAI", calculateSafetyCost("CHENNAI", "MUMBAI", 5, 1.15),
            "GUWAHATI", calculateSafetyCost("CHENNAI", "GUWAHATI", 30, 1.4)
        ));
        GRAPH.put("SRINAGAR", Map.of(
            "DELHI", calculateSafetyCost("SRINAGAR", "DELHI", 80, 1.5),
            "LEH", calculateSafetyCost("SRINAGAR", "LEH", 40, 1.2)
        ));
        GRAPH.put("LEH", Map.of(
            "DELHI", calculateSafetyCost("LEH", "DELHI", 90, 1.8),
            "SRINAGAR", calculateSafetyCost("LEH", "SRINAGAR", 40, 1.2)
        ));
        GRAPH.put("GUWAHATI", Map.of(
            "DELHI", calculateSafetyCost("GUWAHATI", "DELHI", 20, 1.3),
            "CHENNAI", calculateSafetyCost("GUWAHATI", "CHENNAI", 30, 1.4)
        ));
    }

    public OptimizationResponse optimize(OptimizationRequest request) {
        String start = request.getStartLocation();
        String end = request.getEndLocation();

        DijkstraResult result = dijkstra(start, end);
        if (result.path == null) {
            return new OptimizationResponse(null, Double.MAX_VALUE, 0, Integer.MAX_VALUE, "No path found", start, end);
        }

        // Logistics Calculation
        double averageSpeedKPH = 50;
        double totalDrivingHours = result.distance / averageSpeedKPH;
        double baseDays = (totalDrivingHours / 12) / request.getRoadCondition();
        double totalLoadTons = (request.getTroops() * 0.05) + request.getWeaponsSupply();
        int requiredVehicles = (int) Math.ceil(totalLoadTons / 5);
        int vehicleDeficiency = Math.max(0, requiredVehicles - request.getVehicles());
        double foodBufferDays = request.getFoodSupply() - baseDays;
        double logisticalRisk = (vehicleDeficiency * 5) + (Math.max(0, -foodBufferDays) * 10);
        double finalRiskScore = logisticalRisk + (result.cost / 1000);

        String analysis = generateAnalysis(finalRiskScore, requiredVehicles, foodBufferDays, result);

        return new OptimizationResponse(result.path, result.cost, finalRiskScore, (int) Math.ceil(baseDays), analysis, start, end);
    }

    private static double calculateSafetyCost(String locA, String locB, double riskIndex, double roadFactor) {
        double dist = getDistance(LOCATIONS.get(locA), LOCATIONS.get(locB));
        return (dist * roadFactor) + riskIndex * 10;
    }

    private static double getDistance(Location loc1, Location loc2) {
        double R = 6371; // Radius of Earth in kilometers
        double dLat = Math.toRadians(loc2.lat - loc1.lat);
        double dLon = Math.toRadians(loc2.lng - loc1.lng);
        double a = Math.sin(dLat / 2) * Math.sin(dLat / 2) +
                   Math.cos(Math.toRadians(loc1.lat)) * Math.cos(Math.toRadians(loc2.lat)) *
                   Math.sin(dLon / 2) * Math.sin(dLon / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        return R * c;
    }

    private DijkstraResult dijkstra(String start, String end) {
        Map<String, Double> costs = new HashMap<>();
        Map<String, Double> distances = new HashMap<>();
        Map<String, String> previous = new HashMap<>();
        PriorityQueue<Node> pq = new PriorityQueue<>(Comparator.comparingDouble(n -> n.cost));

        for (String node : LOCATIONS.keySet()) {
            costs.put(node, Double.MAX_VALUE);
            distances.put(node, Double.MAX_VALUE);
            previous.put(node, null);
        }

        costs.put(start, 0.0);
        distances.put(start, 0.0);
        pq.add(new Node(start, 0.0));

        while (!pq.isEmpty()) {
            Node smallest = pq.poll();
            String current = smallest.node;

            if (current.equals(end)) {
                List<String> path = new ArrayList<>();
                String temp = end;
                while (temp != null) {
                    path.add(0, temp);
                    temp = previous.get(temp);
                }
                return new DijkstraResult(path, costs.get(end), distances.get(end));
            }

            if (costs.get(current) == Double.MAX_VALUE) continue;

            for (Map.Entry<String, Double> neighbor : GRAPH.getOrDefault(current, Map.of()).entrySet()) {
                double newCost = costs.get(current) + neighbor.getValue();
                double distA = getDistance(LOCATIONS.get(current), LOCATIONS.get(neighbor.getKey()));
                double newDistance = distances.get(current) + (distA * 1.3);

                if (newCost < costs.get(neighbor.getKey())) {
                    costs.put(neighbor.getKey(), newCost);
                    distances.put(neighbor.getKey(), newDistance);
                    previous.put(neighbor.getKey(), current);
                    pq.add(new Node(neighbor.getKey(), newCost));
                }
            }
        }

        return new DijkstraResult(null, Double.MAX_VALUE, Double.MAX_VALUE);
    }

    private String generateAnalysis(double finalRiskScore, int requiredVehicles, double foodBufferDays, DijkstraResult result) {
        String color = finalRiskScore < 5 ? "#2ecc71" : (finalRiskScore < 15 ? "#f39c12" : "#e74c3c");
        String efficiencyText = finalRiskScore < 5 ? "HIGH" : (finalRiskScore < 15 ? "MEDIUM" : "LOW (CRITICAL)");
        String feedback = finalRiskScore < 5 ? "Optimal balance. Route chosen minimizes safety cost, and resources match estimated travel time/load." :
                          (finalRiskScore < 15 ? "Minor logistical concerns or a slightly higher route safety cost. Recommended vehicles: " + requiredVehicles + ". Suggest increasing buffer resources." :
                          "**CRITICAL RISK:** High logistical deficiency OR the safest route itself has a very high base cost. Required vehicles: " + requiredVehicles + ". Food supply buffer: " + String.format("%.1f", foodBufferDays) + " days.");

        return "<p><strong>Logistics Risk Level:</strong> <span style=\"color: " + color + ";\">" + efficiencyText + "</span></p>" +
               "<p><strong>Safest Route (Minimizing Cost):</strong> " + result.path.stream().reduce((a, b) -> a + " &rarr; " + b).orElse("") + "</p>" +
               "<p><strong>Total Safety Cost (Route Index):</strong> " + String.format("%.0f", result.cost) + "</p>" +
               "<p><strong>Estimated Road Distance:</strong> " + String.format("%.0f", result.distance) + " km</p>" +
               "<p><strong>Estimated Travel Time (Days):</strong> " + String.format("%.1f", result.distance / 50 / 12) + " days</p>" +
               "<p><strong>Required Vehicles (for load):</strong> " + requiredVehicles + "</p>" +
               "<p><strong>Food Supply Buffer:</strong> " + String.format("%.1f", foodBufferDays) + " days</p>" +
               "<p><strong>Analysis:</strong> " + feedback + "</p>";
    }

    private static class Location {
        String name;
        double lat, lng;

        Location(String name, double lat, double lng) {
            this.name = name;
            this.lat = lat;
            this.lng = lng;
        }
    }

    private static class Node {
        String node;
        double cost;

        Node(String node, double cost) {
            this.node = node;
            this.cost = cost;
        }
    }

    private static class DijkstraResult {
        List<String> path;
        double cost;
        double distance;

        DijkstraResult(List<String> path, double cost, double distance) {
            this.path = path;
            this.cost = cost;
            this.distance = distance;
        }
    }
}
