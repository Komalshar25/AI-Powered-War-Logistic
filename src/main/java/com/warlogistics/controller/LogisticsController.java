package com.warlogistics.controller;

import com.warlogistics.dto.LogisticsRequest;
import com.warlogistics.dto.LogisticsResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/logistics")
public class LogisticsController {

    @PostMapping("/calculate")
    public ResponseEntity<LogisticsResponse> calculate(@RequestBody LogisticsRequest req) {
        LogisticsResponse r = new LogisticsResponse();
        r.setMessage("Not implemented");
        return ResponseEntity.ok(r);
    }
}
package com.warlogistics.controller;

import com.warlogistics.dto.LogisticsRequest;
import com.warlogistics.dto.LogisticsResponse;
import com.warlogistics.service.LogisticsService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/logistics")
public class LogisticsController {

    private final LogisticsService logisticsService;

    public LogisticsController(LogisticsService logisticsService) {
        this.logisticsService = logisticsService;
    }

    @PostMapping("/create")
    public ResponseEntity<String> createLogistics(@RequestBody LogisticsRequest request) {
        logisticsService.createLogistics(request);
        return ResponseEntity.ok("Logistics created successfully");
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getLogistics(@PathVariable Long id) {
        return ResponseEntity.ok(logisticsService.getLogistics(id));
    }

    @PostMapping("/calculate")
    public ResponseEntity<LogisticsResponse> calculateRoute(@RequestBody LogisticsRequest request) {
        LogisticsResponse response = logisticsService.calculateRouteResponse(request);
        return ResponseEntity.ok(response);
    }
}
