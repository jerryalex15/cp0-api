package com.example.cp0_api.service;

import com.example.cp0_api.dto.ThematicRequest;
import com.example.cp0_api.dto.ThematicResponseBase;
import com.example.cp0_api.dto.ThematicResponseLight;
import com.example.cp0_api.model.Thematic;

public interface ThematicService {
    public ThematicResponseBase getThematic(Long id, boolean includeCircuit);

    public ThematicResponseLight create(ThematicRequest thematicRequest);
}
