package be.kdg.java2.gameproject.threading;

import be.kdg.java2.gameproject.model.Game;
import be.kdg.java2.gameproject.model.GameFactory;

import java.util.List;
import java.util.concurrent.Callable;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class GameCallable implements Callable<List<Game>> {

    private List<Game> games;
    private Predicate<Game> filter;

    public GameCallable(Predicate<Game> filter){
        this.filter = filter;
    }

    @Override
    public List<Game> call() {
        games = Stream.generate(GameFactory::newRandomGame)
                .filter(filter)
                .limit(1000)
                .collect(Collectors.toList());;
        return games;
    }
}
