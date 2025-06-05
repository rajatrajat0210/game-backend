package com.example.demo.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

// entity class for player progression in the game
@Entity
public class Progression {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int level;
    private int rank;
    private int gold;
    private int cash;
    private int gem;

    private LocalDateTime lastActiveTime;

    @ElementCollection
    private List<String> rewardsCollected;

    @ManyToOne
    @JoinColumn(name = "player_id")
    private Player player;

    public Progression() {
    }

    public Progression(int level, int rank, int gold, int cash, int gem,
                       LocalDateTime lastActiveTime, List<String> rewardsCollected, Player player) {
        this.level = level;
        this.rank = rank;
        this.gold = gold;
        this.cash = cash;
        this.gem = gem;
        this.lastActiveTime = lastActiveTime;
        this.rewardsCollected = rewardsCollected;
        this.player = player;
    }

    public Long getId() {
        return id;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    public int getGold() {
        return gold;
    }

    public void setGold(int gold) {
        this.gold = gold;
    }

    public int getCash() {
        return cash;
    }

    public void setCash(int cash) {
        this.cash = cash;
    }

    public int getGem() {
        return gem;
    }

    public void setGem(int gem) {
        this.gem = gem;
    }

    public LocalDateTime getLastActiveTime() {
        return lastActiveTime;
    }

    public void setLastActiveTime(LocalDateTime lastActiveTime) {
        this.lastActiveTime = lastActiveTime;
    }

    public List<String> getRewardsCollected() {
        return rewardsCollected;
    }

    public void setRewardsCollected(List<String> rewardsCollected) {
        this.rewardsCollected = rewardsCollected;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }
}

