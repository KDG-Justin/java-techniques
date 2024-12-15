package be.kdg.java2.gameproject;

import be.kdg.java2.gameproject.model.Game;
import be.kdg.java2.gameproject.model.GameFactory;
import be.kdg.java2.gameproject.model.Genre;
import be.kdg.java2.gameproject.threading.GameAttacker;
import be.kdg.java2.gameproject.threading.GameRunnable;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Demo9 {
    public static int TESTCOUNT;
    public static long totalDuration = 0;

    public static void main(String[] args) {
        for (int i = 0; i < 100; i++) {
            long start = System.currentTimeMillis();
            GameRunnable horrorRun = new GameRunnable(h -> h.getGenre().equals(Genre.HORROR));
            GameRunnable cOnlyRun = new GameRunnable(c -> c.getName().startsWith("C"));
            GameRunnable fpsRun = new GameRunnable(f -> f.getGenre().equals(Genre.FPS));

            Thread horror = new Thread(horrorRun, "horror");
            Thread cOnly = new Thread(cOnlyRun, "startWithC");
            Thread fps = new Thread(fpsRun, "fps");

            horror.start();
            cOnly.start();
            fps.start();

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            horrorRun.getGames().stream().limit(5).forEach(System.out::println);
            System.out.println();
            cOnlyRun.getGames().stream().limit(5).forEach(System.out::println);
            System.out.println();
            fpsRun.getGames().stream().limit(5).forEach(System.out::println);
            long end = System.currentTimeMillis() - start;


            totalDuration += end;
            TESTCOUNT++;
        }
        long gemiddelde = totalDuration / TESTCOUNT;
        //3 threads verzamelen elk 1000 dictators (gemiddelde uit 100 runs): 72,2 ms
        System.out.printf("3 threads verzamelen elk 1000 games (gem van %d runs) = %d ms", TESTCOUNT, gemiddelde);

    }
}
