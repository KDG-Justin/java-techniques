package be.kdg.java2.gameproject.threading;

import be.kdg.java2.gameproject.model.Game;
import be.kdg.java2.gameproject.model.GameFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class GameRunnable implements Runnable{

    private List<Game> games;
    private Predicate<Game> filtered;

    public GameRunnable(Predicate<Game> filterdGames) {
        this.filtered = filterdGames;
    }

    public List<Game> getGames() {
        return games;
    }

    @Override
    public void run() {
        games = Stream.generate(GameFactory::newRandomGame)
                .filter(filtered)
                .limit(1000)
                .collect(Collectors.toList());
    }

}
