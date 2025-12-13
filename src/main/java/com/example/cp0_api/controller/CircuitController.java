package com.example.cp0_api.controller;

import com.example.cp0_api.dto.CircuitRequest;
import com.example.cp0_api.dto.CircuitResponse;
import com.example.cp0_api.service.CircuitService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/circuits")
@RequiredArgsConstructor
public class CircuitController {

    private final CircuitService circuitService;

    @PostMapping
    public CircuitResponse createCircuit(@RequestBody CircuitRequest request) {
        return circuitService.createCircuit(request);
    }

    @GetMapping("/{id}")
    public CircuitResponse getCircuit(@PathVariable Long id) {
        return circuitService.getCircuitById(id);
    }

    @GetMapping
    public List<CircuitResponse> getAllCircuit() {
        return circuitService.getAllCircuit();
    }

    // Optionnel : pagination professionnelle
    @GetMapping("/page")
    public Page<CircuitResponse> getCircuitsPage(Pageable pageable) {
        return circuitService.getAllCircuitPage(pageable);
    }

}
