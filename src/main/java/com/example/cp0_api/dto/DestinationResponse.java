package com.example.cp0_api.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DestinationResponse {
    private Long idDestination;
    private String nomDestination;
    private String illustration;
    private Double prixParJour;
}