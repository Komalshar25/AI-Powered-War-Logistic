<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Logistics Calculator and Map Visualizer (Safest Route) - OpenLayers</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/gh/openlayers/openlayers.github.io@master/en/v6.15.1/css/ol.css">
    <link rel="stylesheet" href="./../static/css/styles.css">
</head>
<body>
<div class="container">
    <div class="control-panel">
        <h1>🇮🇳 Safest Route Logistics & Map Visualizer (OpenLayers)</h1>
        <form action="/optimize" method="post">
            <div class="input-grid">
                <div class="input-group">
                    <label for="startLocation">Start Base:</label>
                    <select id="startLocation" name="startLocation">
                        <c:forEach var="loc" items="${locations}">
                            <option value="${loc.key}" ${loc.key == startKey ? 'selected' : ''}>${loc.value.name}</option>
                        </c:forEach>
                    </select>
                </div>
                <div class="input-group">
                    <label for="endLocation">Target Sector:</label>
                    <select id="endLocation" name="endLocation">
                        <c:forEach var="loc" items="${locations}">
                            <option value="${loc.key}" ${loc.key == endKey ? 'selected' : ''}>${loc.value.name}</option>
                        </c:forEach>
                    </select>
                </div>
                <div class="input-group">
                    <label for="troops">Troops (Personnel):</label>
                    <input type="number" id="troops" name="troops" value="${troops != null ? troops : 100}" min="10" max="1000">
                </div>
                <div class="input-group">
                    <label for="vehicles">Vehicles (Trucks/APCs):</label>
                    <input type="number" id="vehicles" name="vehicles" value="${vehicles != null ? vehicles : 10}" min="1" max="50">
                </div>
                <div class="input-group">
                    <label for="foodSupply">Food Supply (Days):</label>
                    <input type="number" id="foodSupply" name="foodSupply" value="${foodSupply != null ? foodSupply : 7}" min="1" max="30">
                </div>
                <div class="input-group">
                    <label for="weaponsSupply">Weapons Supply (Tons):</label>
                    <input type="number" id="weaponsSupply" name="weaponsSupply" value="${weaponsSupply != null ? weaponsSupply : 5}" min="1" max="50">
                </div>
                <div class="input-group">
                    <label for="roadCondition">Road Condition (Simulated):</label>
                    <select id="roadCondition" name="roadCondition">
                        <option value="1.0" ${roadCondition == 1.0 ? 'selected' : ''}>Excellent (1.0x speed)</option>
                        <option value="0.8" ${roadCondition == 0.8 ? 'selected' : ''}>Normal (0.8x speed)</option>
                        <option value="0.5" ${roadCondition == 0.5 ? 'selected' : ''}>Difficult (0.5x speed)</option>
                    </select>
                </div>
            </div>
            <button type="submit">Find Safest Route & Calculate Logistics</button>
        </form>
        <div class="results">
            <h2>Logistics Analysis</h2>
            <div class="output-box" id="resultsOutput">
                ${results}
            </div>
        </div>
    </div>
    <div id="map"></div>
</div>
<script src="https://cdn.jsdelivr.net/gh/openlayers/openlayers.github.io@master/en/v6.15.1/build/ol.js"></script>
<script src="/js/map.js"></script>
<script>
    window.onload = function() {
        const path = ${path != null ? "['" + path.join("','") + "']" : '[]'};
        const startKey = "${startKey != null ? startKey : ''}";
        const endKey = "${endKey != null ? endKey : ''}";
        const troops = ${troops != null ? troops : 0};
        const weaponsSupply = ${weaponsSupply != null ? weaponsSupply : 0};
        initializeMap(path, startKey, endKey, troops, weaponsSupply);
    };
</script>
</body>
</html>