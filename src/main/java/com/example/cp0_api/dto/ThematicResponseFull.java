package com.example.cp0_api.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public final class ThematicResponseFull implements ThematicResponseBase {
    private Long idThematic;
    private String nomThematic;
    private List<CircuitResponse> circuits;
}
