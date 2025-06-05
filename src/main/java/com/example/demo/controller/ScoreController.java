package com.example.demo.controller;

import com.example.demo.model.Player;
import com.example.demo.model.Score;
import com.example.demo.repository.PlayerRepository;
import com.example.demo.repository.ScoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/scores")
public class ScoreController {

    @Autowired
    private ScoreRepository scoreRepository;

    @Autowired
    private PlayerRepository playerRepository;

    // Submit a new score
    @CacheEvict(value = {"topGlobalScores", "topScoresByGame", "topScoresByCountryAndGame"}, allEntries = true)
    @PostMapping("/submit/{playerId}")
    public Score submitScore(@PathVariable Long playerId, @RequestBody Score score) {
        Player player = playerRepository.findById(playerId)
                .orElseThrow(() -> new RuntimeException("Player not found"));

        score.setPlayer(player);
        if (score.getPlayed() == null) {
            score.setPlayed(LocalDateTime.now());
        }
        if (score.getCountry() == null || score.getCountry().isEmpty()) {
            score.setCountry(player.getCountry()); // Use player's country if not provided
        }
        return scoreRepository.save(score);
    }

    // Get top X scores globally (all games)
    // Cache global top scores with key 'topGlobal' and TTL (configured separately)
    @Cacheable(value = "topGlobalScores", key = "#limit")
    @GetMapping("/top/global")
    public List<Score> getTopGlobalScores(@RequestParam(defaultValue = "10") int limit) {
        System.out.println("Fetching from DB...");
        return scoreRepository.findAll()
                .stream()
                .sorted((s1, s2) -> Integer.compare(s2.getScore(), s1.getScore()))
                .limit(limit)
                .toList();
    }

    // Get top X scores for a specific game globally
    @Cacheable(value = "topScoresByGame", key = "#gameId + '-' + #limit")
    @GetMapping("/top/game/{gameId}")
    public List<Score> getTopScoresByGame(@PathVariable Long gameId, @RequestParam(defaultValue = "10") int limit) {
        System.out.println("Fetching from DB...");
        return scoreRepository.findAll()
                .stream()
                .filter(score -> score.getGameId().equals(gameId))
                .sorted((s1, s2) -> Integer.compare(s2.getScore(), s1.getScore()))
                .limit(limit)
                .toList();
    }

    // Get top X scores by country and game
    // Cache top scores by country and game
    @Cacheable(value = "topScoresByCountryAndGame", key = "#gameId + '-' + #country + '-' + #limit")
    @GetMapping("/top/game/{gameId}/country/{country}")
    public List<Score> getTopScoresByCountryAndGame(@PathVariable Long gameId,
                                                    @PathVariable String country,
                                                    @RequestParam(defaultValue = "10") int limit) {
                                                        System.out.println("Fetching from DB...");
        return scoreRepository.findAll()
                .stream()
                .filter(score -> score.getGameId().equals(gameId) && score.getCountry().equalsIgnoreCase(country))
                .sorted((s1, s2) -> Integer.compare(s2.getScore(), s1.getScore()))
                .limit(limit)
                .toList();
    }
}
