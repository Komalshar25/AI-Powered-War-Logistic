package com.warlogistics;

import com.warlogistics.dto.OptimizationRequest;
import com.warlogistics.dto.OptimizationResponse;
import com.warlogistics.service.OptimizationService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class OptimizationServiceTest {

    @Autowired
    private OptimizationService optimizationService;

    @Test
    public void testOptimize() {
        OptimizationRequest request = new OptimizationRequest("A", "B", 1, 100, 50, 24, 0.8);
        OptimizationResponse response = optimizationService.optimize(request);
        assertNotNull(response);
        // Add more assertions as needed
    }
}
