package com.splitbit.splitbit.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class GameController {
    private List<Game> games;

    public GameController() {
        loadGames();
    }

    @GetMapping("/recommendations")
    public List<Game> getRecommendations(
            @RequestParam(value = "genre", required = false) String genre,
            @RequestParam(value = "rating", required = false) Double rating,
            @RequestParam(value = "price", required = false) Double price
    ) {
        List<Game> filteredGames = games;

        if (genre != null) {
            filteredGames = filteredGames.stream()
                    .filter(game -> game.getGenre().equalsIgnoreCase(genre))
                    .collect(Collectors.toList());
        }

        if (rating != null) {
            filteredGames = filteredGames.stream()
                    .filter(game -> game.getRating() >= rating)
                    .collect(Collectors.toList());
        }

        if (price != null) {
            filteredGames = filteredGames.stream()
                    .filter(game -> game.getPrice() <= price)
                    .collect(Collectors.toList());
        }

        return filteredGames;
    }

    @GetMapping("/games")
    public List<Game> getAllGames() {
        return games;
    }

    private void loadGames() {
        games = new ArrayList<>();

        // Create game objects and add them to the list
        games.add(new Game("Game 1", "Action", "PC", 19.99, 4.5));
        games.add(new Game("Game 2", "RPG", "PS4", 49.99, 4.0));
        games.add(new Game("Game 3", "Adventure", "Xbox One", 29.99, 4.2));
        // Add more games as needed
    }

    static class Game {
        private String name;
        private String genre;
        private String platform;
        private double price;
        private double rating;

        public Game(String name, String genre, String platform, double price, double rating) {
            this.name = name;
            this.genre = genre;
            this.platform = platform;
            this.price = price;
            this.rating = rating;
        }

        public String getName() {
            return name;
        }

        public String getGenre() {
            return genre;
        }

        public String getPlatform() {
            return platform;
        }

        public double getPrice() {
            return price;
        }

        public double getRating() {
            return rating;
        }
    }
}