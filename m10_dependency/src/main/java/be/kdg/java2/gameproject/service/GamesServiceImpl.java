package be.kdg.java2.gameproject.service;

import be.kdg.java2.gameproject.database.GameDao;
import be.kdg.java2.gameproject.model.Game;

import java.util.List;

public class GamesServiceImpl implements GamesService{

    private GameDao gameDao;

    public GamesServiceImpl(GameDao gameDao){
        this.gameDao = gameDao;
    }
    @Override
    public List<Game> getAllGames() {
        return gameDao.getAllGames();
    }

    @Override
    public void addGame(Game game) {
        gameDao.insert(game);
    }
}
