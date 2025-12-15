package com.example.cp0_api.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(
        description = "Base type for Thematic responses",
        oneOf = {ThematicResponseLight.class, ThematicResponseFull.class}
)
public sealed interface ThematicResponseBase
        permits ThematicResponseLight, ThematicResponseFull {
}
