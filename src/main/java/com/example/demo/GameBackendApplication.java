package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class GameBackendApplication {
    public static void main(String[] args) {
        SpringApplication.run(GameBackendApplication.class, args);
    }
}




