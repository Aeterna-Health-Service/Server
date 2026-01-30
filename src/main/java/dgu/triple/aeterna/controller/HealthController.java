package dgu.triple.aeterna.controller;

import dgu.triple.aeterna.dto.ResponseDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class HealthController {
    
    @GetMapping("/health")
    public ResponseDto<Map<String, Object>> health() {
        Map<String, Object> healthData = new HashMap<>();
        healthData.put("status", "UP");
        healthData.put("service", "Aeterna Spring Boot");
        healthData.put("timestamp", System.currentTimeMillis());
        System.out.println("Health Check Success");
        return ResponseDto.ok(healthData);
    }
}

