package com.example.cp0_api.service;

import com.example.cp0_api.controller.CircuitController;
import com.example.cp0_api.dto.CircuitRequest;
import com.example.cp0_api.dto.CircuitResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CircuitService {
    CircuitResponse createCircuit(CircuitRequest request);
    CircuitResponse getCircuitById(Long id);
    List<CircuitResponse> getAllCircuit();
    Page<CircuitResponse> getAllCircuitPage(Pageable pageable);
}
