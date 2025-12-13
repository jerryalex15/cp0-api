package com.example.cp0_api.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.proxy.HibernateProxy;

import java.util.List;
import java.util.Objects;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor  // Génère un constructeur vide
@AllArgsConstructor // Génère un constructeur avec tous les arguments
@Builder            // Génère le pattern Builder
public class Destination {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idDestination;

    private String nomDestination;
    private Double prixParJour;

    @OneToMany(mappedBy = "destination")
    @ToString.Exclude
    private List<Illustration> illustrations;

    @OneToMany(mappedBy = "destination")
    @ToString.Exclude
    private List<CircuitDestination> circuitDestination;

    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        Class<?> oEffectiveClass = o instanceof HibernateProxy ? ((HibernateProxy) o).getHibernateLazyInitializer().getPersistentClass() : o.getClass();
        Class<?> thisEffectiveClass = this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass() : this.getClass();
        if (thisEffectiveClass != oEffectiveClass) return false;
        Destination that = (Destination) o;
        return getIdDestination() != null && Objects.equals(getIdDestination(), that.getIdDestination());
    }

    @Override
    public final int hashCode() {
        return this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass().hashCode() : getClass().hashCode();
    }
}
