package com.example.cp0_api.controller;

import com.example.cp0_api.dto.ThematicRequest;
import com.example.cp0_api.dto.ThematicResponseBase;
import com.example.cp0_api.dto.ThematicResponseLight;
import com.example.cp0_api.service.ThematicService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/api/thematics")
@RequiredArgsConstructor
public class ThematicController {

    private final ThematicService thematicService;

    @PostMapping
    public ResponseEntity<ThematicResponseLight> createThematic(
            @RequestBody ThematicRequest thematicRequest) {

        ThematicResponseLight created = thematicService.create(thematicRequest);

        // Optionnel : construire l'URI du nouvel objet
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(created.getIdThematic())
                .toUri();

        return ResponseEntity.created(location).body(created);
    }

    @Operation(summary = "Returns a thematic")
    @GetMapping("/{id}")
    public ResponseEntity<ThematicResponseBase> getThematic(
            @PathVariable Long id,
            @RequestParam(defaultValue = "false") boolean includeCircuit) {
        try {
            ThematicResponseBase thematic = thematicService.getThematic(id, includeCircuit);
            return ResponseEntity.ok(thematic);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

}
