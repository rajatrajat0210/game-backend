package com.example.demo.repository;

import com.example.demo.model.Progression;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

// interface for Progression repository
@Repository
public interface ProgressionRepository extends JpaRepository<Progression, Long> {
    List<Progression> findByPlayerId(Long playerId);
}


