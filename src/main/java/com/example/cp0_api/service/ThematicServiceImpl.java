package com.example.cp0_api.service;

import com.example.cp0_api.dto.*;
import com.example.cp0_api.exception.DuplicateResourceException;
import com.example.cp0_api.mapper.IAppMapper;
import com.example.cp0_api.model.Thematic;
import com.example.cp0_api.repository.ThematicRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ThematicServiceImpl implements ThematicService{

    private final ThematicRepository thematicRepository;

    private final IAppMapper mapper;

    /**
     * Retrieves a thematic by its ID.
     *
     * @param id The ID of the thematic to retrieve.
     * @param includeCircuit If true, includes the circuits in the response; otherwise, returns a light version.
     * @return Either a ThematicResponseFull or a ThematicResponseLight, depending on the parameter.
     */
    @Override
    public ThematicResponseBase getThematic(Long id, boolean includeCircuit) {
        Thematic thematic = thematicRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Thematic with id : "+ id +" not found"));
        if (!includeCircuit) {
            return mapper.toThematicResponseLight(thematic);
        }
        return mapper.toThematicResponseFull(thematic);
    }

    /**
     * @param thematicRequest is given through the API request
     * @return the ThematicResponseLight created
     */
    @Override
    public ThematicResponseLight create(ThematicRequest thematicRequest) {
        if (thematicRepository.existsByNomThematic(thematicRequest.getNomThematic())){
            throw new DuplicateResourceException(
                    "Thematic '" + thematicRequest.getNomThematic() + "' already exist."
            );
        }

        Thematic thematic = new Thematic();
        thematic.setNomThematic(thematicRequest.getNomThematic());

        Thematic saved = thematicRepository.save(thematic);
        return mapper.toThematicResponseLight(saved);
    }
}
