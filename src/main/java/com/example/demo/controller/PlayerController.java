package com.example.demo.controller;

import com.example.demo.model.Player;
import com.example.demo.repository.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/players")
public class PlayerController {

    @Autowired
    private PlayerRepository playerRepository;

    // Create/register a new player
    @PostMapping("/register")
    public Player registerPlayer(@RequestBody Player player) {
        player.setCreationDate(LocalDateTime.now());  // set creation date on registration
        return playerRepository.save(player);
    }

    // Get all players
    @GetMapping
    public List<Player> getAllPlayers() {
        return playerRepository.findAll();
    }

    // Get player by id
    @GetMapping("/{id}")
    public Optional<Player> getPlayerById(@PathVariable Long id) {
        return playerRepository.findById(id);
    }

    // Update player info
    @PutMapping("/{id}")
    public Player updatePlayer(@PathVariable Long id, @RequestBody Player playerDetails) {
        Player player = playerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Player not found"));
        player.setDeviceId(playerDetails.getDeviceId());
        player.setUsername(playerDetails.getUsername());
        player.setPlatform(playerDetails.getPlatform());
        // Creation date usually not updated
        return playerRepository.save(player);
    }
}

