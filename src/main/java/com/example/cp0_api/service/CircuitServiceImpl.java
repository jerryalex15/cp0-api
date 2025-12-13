package com.example.cp0_api.service;

import com.example.cp0_api.dto.CircuitRequest;
import com.example.cp0_api.dto.CircuitResponse;
import com.example.cp0_api.mapper.IAppMapper;
import com.example.cp0_api.model.Circuit;
import com.example.cp0_api.model.CircuitDestination;
import com.example.cp0_api.model.Destination;
import com.example.cp0_api.model.Thematic;
import com.example.cp0_api.repository.CircuitRepository;
import com.example.cp0_api.repository.DestinationRepository;
import com.example.cp0_api.repository.ThematicRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CircuitServiceImpl implements CircuitService {

    private final CircuitRepository circuitRepository;
    private final DestinationRepository destinationRepository;
    private final ThematicRepository thematicRepository;
    private final IAppMapper mapper;

    /**
     * CREATE CIRCUIT
     *
     * @param request Take the circuitRequest
     * @return CircuitResponse created
     */
    @Override
    public CircuitResponse createCircuit(CircuitRequest request) {

        // Mapping Request -> Entity
        Circuit circuit = Circuit.builder()
                .nomCircuit(request.getNomCircuit())
                .nbrDeJour(request.getNbrDeJour())
                .build();
        // Create the link circuit-destination
        List<CircuitDestination> circuitDestinations = request.getDestinations().stream()
                .map(d -> {
                    Destination dest = destinationRepository.findById(d.getDestinationId()).orElseThrow(
                            ()-> new RuntimeException("Destination with id " + d.getDestinationId() + "not found"));

                    CircuitDestination cd = new CircuitDestination();
                    cd.setCircuit(circuit);
                    cd.setDestination(dest);
                    cd.setNbrJour(d.getNbrJour());
                    return cd;
                })
                .toList();

        circuit.setCircuitDestinations(circuitDestinations);

        List<Thematic> thematics = thematicRepository.findAllById(request.getThematicIds());
        circuit.setThematics(thematics);

        // Save en DB
        Circuit saved = circuitRepository.save(circuit);

        // Retourner le DTO
        CircuitResponse response = mapper.toCircuitResponse(saved);

        // Calculer le prix total si nécessaire
        response.setPrixCircuit(calculateCircuitPrice(saved));

        return response;
    }

    /**
     * GET CIRCUIT BY ID
     *
     * @param id of the circuit researched
     * @return CircuitResponse circuit
     */
    @Override
    public CircuitResponse getCircuitById(Long id) {
        Circuit circuit = circuitRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Circuit introuvable : " + id));
        CircuitResponse circuitResponse = mapper.toCircuitResponse(circuit);
        circuitResponse.setPrixCircuit(calculateCircuitPrice(circuit));
        return circuitResponse;
    }

    /**
     * @return all circuit in the database
     */
    @Override
    public List<CircuitResponse> getAllCircuit() {
        List<Circuit> circuits = circuitRepository.findAll();
        List<CircuitResponse> circuitResponses = mapper.toCircuitResponseList(circuits);

        for (int i = 0; i < circuits.size(); i++) {
            double price = this.calculateCircuitPrice(circuits.get(i));
            circuitResponses.get(i).setPrixCircuit(price);
        }
        return circuitResponses;
    }

    /**
     * @param pageable given page
     * @return Page of all circuit
     */
    @Override
    public Page<CircuitResponse> getAllCircuitPage(Pageable pageable) {
        return circuitRepository.findAll(pageable)
                .map(mapper::toCircuitResponse);
    }

    private double calculateCircuitPrice(Circuit c) {
        return c.getCircuitDestinations().stream()
                .mapToDouble(cd ->
                        cd.getNbrJour() * cd.getDestination().getPrixParJour()
                )
                .sum();
    }
}