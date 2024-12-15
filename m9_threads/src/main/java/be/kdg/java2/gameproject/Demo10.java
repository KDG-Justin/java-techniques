package be.kdg.java2.gameproject;

import be.kdg.java2.gameproject.model.Game;
import be.kdg.java2.gameproject.model.GameFactory;
import be.kdg.java2.gameproject.model.Genre;
import be.kdg.java2.gameproject.threading.GameAttacker;

import java.util.ArrayList;
import java.util.List;

public class Demo10 {
    public static void main(String[] args){
        System.out.println("Voor uitzuivering: ");
        List<Game> games = new ArrayList<>();
        for (int i = 0; i<1000; i++) games.add(GameFactory.newRandomGame());


        int metHorror = games.stream().filter(g -> g.getGenre().equals(Genre.HORROR)).toList().size();
        int metFps = games.stream().filter(g -> g.getGenre().equals(Genre.FPS)).toList().size();
        int metC = games.stream().filter(g -> g.getName().startsWith("C")).toList().size();
        System.out.println("List size = "+games.size());
        System.out.println("Aantal horror: " + metHorror);
        System.out.println("Aantal fps: " + metFps);
        System.out.println("Aantal c; "+metC);

        System.out.println();

        Thread attackHorror = new Thread(new GameAttacker(games, g -> g.getGenre().equals(Genre.HORROR)));
        Thread attackFps = new Thread(new GameAttacker(games, g -> g.getGenre().equals(Genre.FPS)));
        Thread attackC = new Thread(new GameAttacker(games, g -> g.getName().startsWith("C")));
        attackHorror.start();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        attackFps.start();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        attackC.start();
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        try {
            attackFps.join();
            attackC.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        int zonderHorror = games.stream().filter(g -> g.getGenre().equals(Genre.HORROR)).toList().size();
        int zonderFps = games.stream().filter(g -> g.getGenre().equals(Genre.FPS)).toList().size();
        int zonderC = games.stream().filter(g -> g.getName().startsWith("C")).toList().size();



        System.out.println("Na uitzuivering");
        System.out.println("Aantal horror: "+ zonderHorror);
        System.out.println("Aantal fps: "+zonderFps);
        System.out.println("Aantal C: "+zonderC);
        System.out.println("List size = "+games.size());

    }
}
