// PlayerRepository.java
package com.example.demo.repository;

import com.example.demo.model.Player;
import org.springframework.data.jpa.repository.JpaRepository;

// interface for Player repository
public interface PlayerRepository extends JpaRepository<Player, Long> {
    Player findByDeviceId(String deviceId);
}

