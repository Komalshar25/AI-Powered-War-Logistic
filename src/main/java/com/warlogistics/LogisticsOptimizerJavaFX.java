package com.warlogistics;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

public class LogisticsOptimizerJavaFX extends Application {

    @Override
    public void start(Stage primaryStage) {
        // Main layout
        BorderPane root = new BorderPane();
        root.setStyle("-fx-background-color: #f0f0f0;");

        // Header
        Label title = new Label("Safest Route Logistics");
        title.setFont(Font.font("Arial", FontWeight.BOLD, 24));
        title.setTextFill(Color.DARKBLUE);
        VBox header = new VBox(title);
        header.setAlignment(Pos.CENTER);
        header.setPadding(new Insets(20));
        header.setStyle("-fx-background-color: #e0e0e0; -fx-border-color: #1e40af; -fx-border-width: 0 0 2 0;");
        root.setTop(header);

        // Center content
        VBox center = new VBox(20);
        center.setPadding(new Insets(20));
        center.setMaxWidth(1000);
        center.setAlignment(Pos.TOP_CENTER);

        // Input Section
        VBox inputSection = new VBox(15);
        inputSection.setPadding(new Insets(20));
        inputSection.setStyle("-fx-background-color: white; -fx-background-radius: 10; -fx-effect: dropshadow(gaussian, rgba(0,0,0,0.1), 10, 0, 0, 5);");

        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setAlignment(Pos.CENTER);

        // Start Base
        Label startLabel = new Label("Start Base");
        ComboBox<String> startBase = new ComboBox<>();
        startBase.getItems().addAll("New Delhi (HQ)", "Mumbai(Port)", "Chennai(Naval)", "Andhra Pradesh", "Arunachal Pradesh", "Assam", "Bihar", "Chhattisgarh", "Goa", "Gujarat", "Haryana", "Himachal Pradesh", "Jharkhand", "Karnataka", "Kerala", "Madhya Pradesh", "Maharashtra", "Manipur", "Meghalaya", "Mizoram", "Nagaland", "Odisha", "Punjab", "Rajasthan", "Sikkim", "Tamil Nadu", "Telangana", "Tripura", "Uttar Pradesh", "Uttarakhand", "West Bengal");
        startBase.setValue("New Delhi (HQ)");
        startBase.setEditable(true);
        startBase.setPrefWidth(200);

        // Target Sector
        Label targetLabel = new Label("Target Sector");
        ComboBox<String> targetSector = new ComboBox<>();
        targetSector.getItems().addAll("Srinagar (Forward Base)", "Leh (High Altitude)", "Guwahati (Eastern Sector)", "Andhra Pradesh", "Arunachal Pradesh", "Assam", "Bihar", "Chhattisgarh", "Goa", "Gujarat", "Haryana", "Himachal Pradesh", "Jharkhand", "Karnataka", "Kerala", "Madhya Pradesh", "Maharashtra", "Manipur", "Meghalaya", "Mizoram", "Nagaland", "Odisha", "Punjab", "Rajasthan", "Sikkim", "Tamil Nadu", "Telangana", "Tripura", "Uttar Pradesh", "Uttarakhand", "West Bengal");
        targetSector.setValue("Srinagar (Forward Base)");
        targetSector.setEditable(true);
        targetSector.setPrefWidth(200);

        // Other inputs
        Label troopsLabel = new Label("Troops (Personnel)");
        Spinner<Integer> troops = new Spinner<>(0, Integer.MAX_VALUE, 0);
        troops.setPrefWidth(200);

        Label vehiclesLabel = new Label("Vehicles (Trucks/APCs)");
        Spinner<Integer> vehicles = new Spinner<>(0, Integer.MAX_VALUE, 0);
        vehicles.setPrefWidth(200);

        Label foodLabel = new Label("Food Supply (Days)");
        Spinner<Integer> food = new Spinner<>(0, Integer.MAX_VALUE, 0);
        food.setPrefWidth(200);

        Label weaponsLabel = new Label("Weapons Supply (Tons)");
        Spinner<Integer> weapons = new Spinner<>(0, Integer.MAX_VALUE, 0);
        weapons.setPrefWidth(200);

        Label roadLabel = new Label("Road Condition (Simulated)");
        ComboBox<String> roadCondition = new ComboBox<>();
        roadCondition.getItems().addAll("Excellent (1.0x speed)", "Good (0.9x speed)", "Fair (0.8x speed)", "Poor (0.7x speed)");
        roadCondition.setValue("Excellent (1.0x speed)");
        roadCondition.setPrefWidth(200);

        grid.add(startLabel, 0, 0);
        grid.add(startBase, 1, 0);
        grid.add(targetLabel, 0, 1);
        grid.add(targetSector, 1, 1);
        grid.add(troopsLabel, 0, 2);
        grid.add(troops, 1, 2);
        grid.add(vehiclesLabel, 0, 3);
        grid.add(vehicles, 1, 3);
        grid.add(foodLabel, 0, 4);
        grid.add(food, 1, 4);
        grid.add(weaponsLabel, 0, 5);
        grid.add(weapons, 1, 5);
        grid.add(roadLabel, 0, 6);
        grid.add(roadCondition, 1, 6);

        Button calculateButton = new Button("Find Safest Route & Calculate Logistics");
        calculateButton.setStyle("-fx-background-color: #1e40af; -fx-text-fill: white; -fx-font-size: 16px; -fx-font-weight: bold;");
        calculateButton.setPrefWidth(400);
        calculateButton.setOnAction(e -> {
            // Simulate calculation
            updateAnalysis(center, startBase.getValue(), targetSector.getValue());
        });

        inputSection.getChildren().addAll(grid, calculateButton);

        // Analysis Section
        VBox analysisSection = new VBox(10);
        analysisSection.setPadding(new Insets(20));
        analysisSection.setStyle("-fx-background-color: white; -fx-background-radius: 10; -fx-effect: dropshadow(gaussian, rgba(0,0,0,0.1), 10, 0, 0, 5);");

        Label analysisTitle = new Label("Logistics Analysis");
        analysisTitle.setFont(Font.font("Arial", FontWeight.BOLD, 18));

        VBox analysisContent = new VBox(5);
        analysisContent.getChildren().addAll(
            new Label("Logistics Risk Level: "),
            new Label("Safest Route (Minimizing Cost): "),
            new Label("Total Safety Cost (Route Index): "),
            new Label("Estimated Road Distance: "),
            new Label("Estimated Travel Time (Days): "),
            new Label("Required Vehicles (for load): "),
            new Label("Food Supply Buffer: "),
            new Label("Analysis: ")
        );

        analysisSection.getChildren().addAll(analysisTitle, analysisContent);

        // Map Section
        WebView mapView = new WebView();
        mapView.setPrefHeight(600);
        mapView.getEngine().loadContent(getMapHTML());

        center.getChildren().addAll(inputSection, analysisSection, mapView);
        root.setCenter(center);

        Scene scene = new Scene(root, 1200, 900);
        primaryStage.setTitle("IN Safest Route Logistics & Map Visualizer");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void updateAnalysis(VBox center, String start, String target) {
        VBox analysisSection = (VBox) center.getChildren().get(1);
        VBox analysisContent = (VBox) analysisSection.getChildren().get(1);

        ((Label) analysisContent.getChildren().get(0)).setText("Logistics Risk Level: HIGH");
        ((Label) analysisContent.getChildren().get(0)).setTextFill(Color.TEAL);
        ((Label) analysisContent.getChildren().get(0)).setFont(Font.font("Arial", FontWeight.BOLD, 14));

        ((Label) analysisContent.getChildren().get(1)).setText("Safest Route (Minimizing Cost): " + start + " → " + target);
        ((Label) analysisContent.getChildren().get(2)).setText("Total Safety Cost (Route Index): 1775");
        ((Label) analysisContent.getChildren().get(3)).setText("Estimated Road Distance: 845 km (based on safest route path)");
        ((Label) analysisContent.getChildren().get(4)).setText("Estimated Travel Time (Days): 1.4 days");
        ((Label) analysisContent.getChildren().get(5)).setText("Required Vehicles (for load): 2");
        ((Label) analysisContent.getChildren().get(6)).setText("Food Supply Buffer: 5.6 days");
        ((Label) analysisContent.getChildren().get(7)).setText("Analysis: Optimal balance. Route chosen minimizes safety cost, and resources match estimated travel time/load.");
    }

    private String getMapHTML() {
        return "<!DOCTYPE html>\n" +
                "<html>\n" +
                "<head>\n" +
                "    <link rel=\"stylesheet\" href=\"https://cdn.jsdelivr.net/npm/ol@v7.4.0/ol.css\">\n" +
                "    <script src=\"https://cdn.jsdelivr.net/npm/ol@v7.4.0/dist/ol.js\"></script>\n" +
                "</head>\n" +
                "<body>\n" +
                "    <div id=\"map\" style=\"width: 100%; height: 600px;\"></div>\n" +
                "    <script>\n" +
                "        const map = new ol.Map({\n" +
                "            target: 'map',\n" +
                "            layers: [new ol.layer.Tile({ source: new ol.source.OSM() })],\n" +
                "            view: new ol.View({ center: ol.proj.fromLonLat([77.10, 30.00]), zoom: 6 })\n" +
                "        });\n" +
                "        const delhiCoords = ol.proj.fromLonLat([77.2090, 28.6139]);\n" +
                "        const srinagarCoords = ol.proj.fromLonLat([74.7973, 34.0837]);\n" +
                "        const routeFeature = new ol.Feature({ geometry: new ol.geom.LineString([delhiCoords, srinagarCoords]) });\n" +
                "        routeFeature.setStyle(new ol.style.Style({ stroke: new ol.style.Stroke({ color: '#1e40af', width: 4 }) }));\n" +
                "        const vectorSource = new ol.source.Vector({ features: [routeFeature] });\n" +
                "        const vectorLayer = new ol.layer.Vector({ source: vectorSource });\n" +
                "        map.addLayer(vectorLayer);\n" +
                "    </script>\n" +
                "</body>\n" +
                "</html>";
    }

    public static void main(String[] args) {
        launch(args);
    }
}
