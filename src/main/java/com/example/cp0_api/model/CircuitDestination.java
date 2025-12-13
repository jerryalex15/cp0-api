package com.example.cp0_api.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor  // Génère un constructeur vide
@AllArgsConstructor // Génère un constructeur avec tous les arguments
@Builder            // Génère le pattern Builder
public class CircuitDestination {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idCircuitDestination;

    private Integer nbrJour;
    private Integer ordre;

    @ManyToOne
    @JoinColumn(name = "idCircuit")
    private Circuit circuit;

    @ManyToOne
    @JoinColumn(name = "idDestination")
    private Destination destination;
}
