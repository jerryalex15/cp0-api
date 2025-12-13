package com.example.cp0_api.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CircuitDestinationResponse {
    private DestinationResponse destination;
    private CircuitResponse circuit;
    private Integer nbrJour;
}
