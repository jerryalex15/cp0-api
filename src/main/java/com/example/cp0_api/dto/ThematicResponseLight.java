package com.example.cp0_api.dto;

import lombok.*;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public final class ThematicResponseLight implements ThematicResponseBase {
    private Long idThematic;
    private String nomThematic;
}
