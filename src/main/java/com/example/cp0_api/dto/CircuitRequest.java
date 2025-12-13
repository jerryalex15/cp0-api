package com.example.cp0_api.dto;

import com.example.cp0_api.model.Thematic;
import lombok.*;

import java.util.HashMap;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CircuitRequest {

    private String nomCircuit;
    private Integer nbrDeJour; // nombre total de jours du circuit, facultatif
    private List<DestinationWithDays> destinations;
    private List<Long> thematicIds;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class DestinationWithDays {
        private Long destinationId;
        private Integer nbrJour; // nombre de jours pour cette destination
    }
}