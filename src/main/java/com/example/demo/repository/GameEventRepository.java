package com.example.demo.repository;

import com.example.demo.model.GameEvent;
import org.springframework.data.jpa.repository.JpaRepository;
import java.time.LocalDateTime;
import java.util.List;

// interface for GameEvent repository
public interface GameEventRepository extends JpaRepository<GameEvent, Long> {
    List<GameEvent> findByStartTimeBeforeAndEndTimeAfter(LocalDateTime now1, LocalDateTime now2);
}


