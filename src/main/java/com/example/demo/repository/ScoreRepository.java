package com.example.demo.repository;

import com.example.demo.model.Score;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
// interface for Score repository
@Repository
public interface ScoreRepository extends JpaRepository<Score, Long> {

    // Find scores by gameId ordered descending by score (for global leaderboard per game)
    List<Score> findTop10ByGameIdOrderByScoreDesc(Long gameId);

    // Find scores by country and gameId ordered descending by score
    List<Score> findTop10ByCountryAndGameIdOrderByScoreDesc(String country, Long gameId);

    // For global leaderboard (all games), get top scores ordered by score desc
    List<Score> findTop10ByOrderByScoreDesc();

}

