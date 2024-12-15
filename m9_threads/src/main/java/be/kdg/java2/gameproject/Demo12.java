package be.kdg.java2.gameproject;

import be.kdg.java2.gameproject.model.Game;
import be.kdg.java2.gameproject.model.GameFactory;
import be.kdg.java2.gameproject.model.Games;
import java.util.List;
import java.util.stream.Stream;

public class Demo12 {
    public static void main(String[] args){
        Games games = new Games(10000);
        Runnable runnable = () -> {
            List<Game> gameList = Stream.generate(GameFactory::newRandomGame).limit(5000).toList();
            gameList.forEach(games::add);
        };

        Thread thread1 = new Thread(runnable);
        Thread thread2 = new Thread(runnable);

        thread1.start();
        thread2.start();

        try {
            thread1.join();
            thread2.join();
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        //moet 10000 zijn
        System.out.printf("Na toevoegen door 2 threads met elk 5000 objecten: games = %d", games.getSize());

    }
}
