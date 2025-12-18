 
 function initializeMap(path, startKey, endKey, troops, weaponsSupply) {
    if (typeof ol === 'undefined' || typeof ol.Map !== 'function') {
        document.getElementById('resultsOutput').innerHTML = '<p style="color: #dc3545;"><strong>Map Creation Error:</strong> The OpenLayers library is not fully initialized. Check CDN link.</p>';
        console.error("OpenLayers object 'ol' not found or ol.Map is not a function.");
        return;
    }

    try {
        const indiaExtentWGS84 = [65, 5, 100, 38];
        const indiaExtent = ol.proj.transformExtent(indiaExtentWGS84, 'EPSG:4326', 'EPSG:3857');

        const vectorSource = new ol.source.Vector();
        const vectorLayer = new ol.layer.Vector({
            source: vectorSource
        });

        const map = new ol.Map({
            target: 'map',
            layers: [
                new ol.layer.Tile({
                    source: new ol.source.OSM()
                }),
                vectorLayer
            ],
            view: new ol.View({
                center: ol.proj.fromLonLat([78.5, 23.5]),
                zoom: 5,
                extent: indiaExtent,
                minZoom: 4
            })
        });

        const LOCATIONS = {
            DELHI: { name: "New Delhi (HQ)", lat: 28.6139, lng: 77.2090 },
            MUMBAI: { name: "Mumbai (Logistics Hub)", lat: 19.0760, lng: 72.8777 },
            CHENNAI: { name: "Chennai (Southern Supply)", lat: 13.0827, lng: 80.2707 },
            SRINAGAR: { name: "Srinagar (Forward Base)", lat: 34.0837, lng: 74.7973 },
            LEH: { name: "Leh (High Altitude)", lat: 34.1526, lng: 77.5770 },
            GUWAHATI: { name: "Guwahati (Eastern Sector)", lat: 26.1445, lng: 91.7368 }
        };

        if (path && path.length > 0) {
            const pathCoords = path.map(key => {
                const loc = LOCATIONS[key];
                return ol.proj.fromLonLat([loc.lng, loc.lat]);
            });

            const routeFeature = new ol.Feature({
                geometry: new ol.geom.LineString(pathCoords)
            });

            routeFeature.setStyle(new ol.style.Style({
                stroke: new ol.style.Stroke({
                    color: '#e74c3c',
                    width: 4
                })
            }));
            vectorSource.addFeature(routeFeature);

            const startLoc = LOCATIONS[startKey];
            const endLoc = LOCATIONS[endKey];

            const startStyle = new ol.style.Style({
                image: new ol.style.Circle({
                    radius: 12,
                    fill: new ol.style.Fill({ color: '#2ecc71' }),
                    stroke: new ol.style.Stroke({ color: '#fff', width: 3 })
                }),
                text: new ol.style.Text({
                    text: startLoc.name,
                    font: 'bold 18px Arial',
                    fill: new ol.style.Fill({ color: '#000' }),
                    stroke: new ol.style.Stroke({ color: '#fff', width: 4 }),
                    offsetY: -25
                })
            });

            const endStyle = new ol.style.Style({
                image: new ol.style.Circle({
                    radius: 12,
                    fill: new ol.style.Fill({ color: '#e74c3c' }),
                    stroke: new ol.style.Stroke({ color: '#fff', width: 3 })
                }),
                text: new ol.style.Text({
                    text: endLoc.name,
                    font: 'bold 18px Arial',
                    fill: new ol.style.Fill({ color: '#000' }),
                    stroke: new ol.style.Stroke({ color: '#fff', width: 4 }),
                    offsetY: -25
                })
            });

            const startCoord = ol.proj.fromLonLat([startLoc.lng, startLoc.lat]);
            const startFeature = new ol.Feature({
                geometry: new ol.geom.Point(startCoord),
                name: `START: ${startLoc.name}`,
                type: 'start'
            });
            startFeature.setStyle(startStyle);
            vectorSource.addFeature(startFeature);

            const endCoord = ol.proj.fromLonLat([endLoc.lng, endLoc.lat]);
            const endFeature = new ol.Feature({
                geometry: new ol.geom.Point(endCoord),
                name: `END: ${endLoc.name}`,
                details: `Target: ${troops} Troops, ${weaponsSupply} Tons.`,
                type: 'end'
            });
            endFeature.setStyle(endStyle);
            vectorSource.addFeature(endFeature);

            map.getView().fit(vectorSource.getExtent(), {
                padding: [50, 50, 50, 50],
                duration: 1000
            });
        }
    } catch (e) {
        document.getElementById('resultsOutput').innerHTML = `<p style="color: #dc3545;"><strong>Map Creation Error:</strong> Map initialization failed: ${e.message}</p>`;
        console.error("Map creation failed:", e);
    }
}