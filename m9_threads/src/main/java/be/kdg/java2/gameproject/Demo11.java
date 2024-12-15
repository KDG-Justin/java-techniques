package be.kdg.java2.gameproject;

import be.kdg.java2.gameproject.model.Game;
import be.kdg.java2.gameproject.model.Genre;
import be.kdg.java2.gameproject.threading.GameCallable;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Demo11 {
    public static void main(String[] args){
        int TEST_COUNT = 0;
        long total = 0;
        for (int i = 0; i<1000; i++) {
            long start = System.currentTimeMillis();
            GameCallable horror = new GameCallable(g -> g.getGenre().equals(Genre.HORROR));
            GameCallable fps = new GameCallable(g -> g.getGenre().equals(Genre.FPS));
            GameCallable a = new GameCallable(g -> g.getName().startsWith("A"));

            ExecutorService pool = Executors.newFixedThreadPool(5);
            List<Future<List<Game>>> futures = new ArrayList<>();

            Future<List<Game>> futureHorror = pool.submit(horror);
            futures.add(futureHorror);

            Future<List<Game>> futureFPS = pool.submit(fps);
            futures.add(futureFPS);

            Future<List<Game>> futureA = pool.submit(a);
            futures.add(futureA);

            pool.shutdown();
            long end = System.currentTimeMillis() - start;
            total += end;

            TEST_COUNT++;

        }

        long gem = total/TEST_COUNT;
        System.out.printf("3 Futures verzamelen elk 1000 games (gemmiddelde uit %d): %d ms", TEST_COUNT, gem);
    }
}
