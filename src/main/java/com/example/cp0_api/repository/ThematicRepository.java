package com.example.cp0_api.repository;

import com.example.cp0_api.model.Thematic;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ThematicRepository extends JpaRepository<Thematic, Long> {
    boolean existsByNomThematic(String nomThematic);

    Optional<Thematic> findByNomThematic(String nomThematic);
}
