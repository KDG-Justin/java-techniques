package be.kdg.java2.gameproject.threading;

import be.kdg.java2.gameproject.model.Game;

import java.util.List;
import java.util.function.Predicate;

public class GameAttacker implements Runnable {
    private List<Game> games;
    private Predicate<Game> predicate;

    public GameAttacker(List<Game> gameList, Predicate<Game> removed) {
        this.games = gameList;
        this.predicate = removed;
    }

    public synchronized void filter(){
        games.removeIf(predicate);
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void run() {
        filter();
        System.out.println("REMOVE SUCCESSFUL! "+Thread.currentThread().getName());
    }
}
