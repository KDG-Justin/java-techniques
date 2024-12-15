package be.kdg.java2.gameproject.service;

import be.kdg.java2.gameproject.model.Game;

import java.util.List;

public interface GamesService {
    List<Game> getAllGames();

    void addGame(Game game);
}
