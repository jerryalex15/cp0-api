package com.example.cp0_api.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor  // Génère un constructeur vide
@AllArgsConstructor // Génère un constructeur avec tous les arguments
@Builder            // Génère le pattern Builder
public class Thematic {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idThematic;
    private String nomThematic;

    @ManyToMany(mappedBy = "thematics")
    @ToString.Exclude
    private List<Circuit> circuits;
}
