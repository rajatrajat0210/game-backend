package com.example.demo.controller;

import com.example.demo.model.Player;
import com.example.demo.model.Progression;
import com.example.demo.repository.PlayerRepository;
import com.example.demo.repository.ProgressionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

// Routing for managing player progressions
// This controller handles saving, updating, and retrieving player progressions
@RestController
@RequestMapping("/progressions")
public class ProgressionController {

    @Autowired
    private ProgressionRepository progressionRepository;

    @Autowired
    private PlayerRepository playerRepository;

    // Save or update progression for a player
    @PostMapping("/save/{playerId}")
    public Progression saveProgression(@PathVariable Long playerId, @RequestBody Progression progression) {
        Player player = playerRepository.findById(playerId)
                .orElseThrow(() -> new RuntimeException("Player not found"));

        progression.setPlayer(player);
        return progressionRepository.save(progression);
    }

    // Get all progressions
    @GetMapping
    // Retrieve all player progressions
    public List<Progression> getAllProgressions() {
        return progressionRepository.findAll();
    }

    // Get progressions by player id
    @GetMapping("/player/{playerId}")
    // Retrieve all progressions for a specific player
    public List<Progression> getProgressionsByPlayer(@PathVariable Long playerId) {
        return progressionRepository.findByPlayerId(playerId);
    }
}

