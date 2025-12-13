package com.example.cp0_api.repository;

import com.example.cp0_api.model.Illustration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IllustrationRepository extends JpaRepository<Illustration, Long> {
}
