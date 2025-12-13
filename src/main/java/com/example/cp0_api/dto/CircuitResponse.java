package com.example.cp0_api.dto;

import com.example.cp0_api.model.CircuitDestination;
import com.example.cp0_api.model.Destination;
import com.example.cp0_api.model.Thematic;
import jakarta.persistence.Transient;
import lombok.*;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CircuitResponse {

    private Long idCircuit;
    private String nomCircuit;
    private Integer nbrDeJour;
    private Double prixCircuit;
    private List<DestinationResponse> circuitDestinations; // Liste des destinations détaillées
    private List<Thematic> thematics;
}