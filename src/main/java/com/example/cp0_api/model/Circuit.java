package com.example.cp0_api.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.proxy.HibernateProxy;

import java.util.List;
import java.util.Objects;

@Entity
@Getter
@Setter
@NoArgsConstructor  // Génère un constructeur vide
@AllArgsConstructor // Génère un constructeur avec tous les arguments
@Builder            // Génère le pattern Builder
public class Circuit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idCircuit;

    private String nomCircuit;
    private Integer nbrDeJour;

    @OneToMany(mappedBy = "circuit", cascade = CascadeType.ALL)
    private List<CircuitDestination> circuitDestinations;

    @ManyToMany
    @JoinTable(
            name = "linkCircuitThematic",
            joinColumns = @JoinColumn(name = "idCircuit"),
            inverseJoinColumns = @JoinColumn(name = "idThematic")
    )
    private List<Thematic> thematics;

    @Transient
    public List<Destination> getDestinations() {
        return circuitDestinations.stream()
                .map(CircuitDestination::getDestination)
                .toList();
    }

    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        Class<?> oEffectiveClass = o instanceof HibernateProxy ? ((HibernateProxy) o).getHibernateLazyInitializer().getPersistentClass() : o.getClass();
        Class<?> thisEffectiveClass = this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass() : this.getClass();
        if (thisEffectiveClass != oEffectiveClass) return false;
        Circuit circuit = (Circuit) o;
        return this.getIdCircuit() != null && Objects.equals(this.getIdCircuit(), circuit.getIdCircuit());
    }

    @Override
    public final int hashCode() {
        return this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass().hashCode() : getClass().hashCode();
    }
}
