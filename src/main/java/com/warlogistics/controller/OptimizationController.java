package com.warlogistics.controller;

import com.warlogistics.dto.OptimizationRequest;
import com.warlogistics.dto.OptimizationResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/optimize")
public class OptimizationController {

    @PostMapping
    public ResponseEntity<OptimizationResponse> optimize(@RequestBody OptimizationRequest req) {
        OptimizationResponse r = new OptimizationResponse();
        r.setSummary("Optimization not implemented yet");
        return ResponseEntity.ok(r);
    }
}
package com.warlogistics.controller;

import com.warlogistics.dto.OptimizationRequest;
import com.warlogistics.dto.OptimizationResponse;
import com.warlogistics.service.OptimizationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class OptimizationController {

    private final OptimizationService optimizationService;

    public OptimizationController(OptimizationService optimizationService) {
        this.optimizationService = optimizationService;
    }

    @PostMapping("/optimize")
    public ResponseEntity<OptimizationResponse> optimize(@RequestBody OptimizationRequest request) {
        OptimizationResponse response = optimizationService.optimize(request);
        return ResponseEntity.ok(response);
    }
}
