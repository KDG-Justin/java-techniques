package be.kdg.java2.gameproject.persist;

import be.kdg.java2.gameproject.model.Game;

import java.util.List;

public interface GameDao {
    boolean insert(Game game);
    boolean delete(String name);
    boolean update(Game game);
    Game retrieve(String name);
    List<Game> sortedOn(String query);
}
