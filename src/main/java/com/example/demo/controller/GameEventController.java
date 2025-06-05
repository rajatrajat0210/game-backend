package com.example.demo.controller;

import com.example.demo.model.GameEvent;
import com.example.demo.repository.GameEventRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/events")
public class GameEventController {

    @Autowired
    private GameEventRepository gameEventRepository;

    // Schedule (Create) a new event
    @CacheEvict(value = "availableEvents", allEntries = true)
    @PostMapping("/schedule")
    public GameEvent scheduleEvent(@RequestBody GameEvent event) {
        return gameEventRepository.save(event);
    }

    // Update an existing event
    @CacheEvict(value = "availableEvents", allEntries = true)
    @PutMapping("/update/{id}")
    public ResponseEntity<GameEvent> updateEvent(@PathVariable Long id, @RequestBody GameEvent updatedEvent) {
        return gameEventRepository.findById(id)
                .map(event -> {
                    event.setName(updatedEvent.getName());
                    event.setStartTime(updatedEvent.getStartTime());
                    event.setEndTime(updatedEvent.getEndTime());
                    event.setConfiguration(updatedEvent.getConfiguration());
                    gameEventRepository.save(event);
                    return ResponseEntity.ok(event);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    // Get currently available events wrt time (valid events)
    @GetMapping("/available")
    @Cacheable("availableEvents") // Cache the results for performance
    public List<GameEvent> getAvailableEvents() {
        System.out.println("Fetching from DB...");
        LocalDateTime now = LocalDateTime.now();
        return gameEventRepository.findByStartTimeBeforeAndEndTimeAfter(now, now);
    }
}
