package be.kdg.java2.gameproject.data;

import be.kdg.java2.gameproject.model.Game;
import be.kdg.java2.gameproject.model.Games;
import be.kdg.java2.gameproject.model.Genre;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Data {
    public static List<Game> getData(){
        List<Game> myList = new ArrayList<>();
        myList.add(new Game("Multiversus", 0.0, Genre.FIGHTING, 42177, LocalDate.of(2022, 7, 19), true));
        myList.add(new Game("COD Modern Warfare 2", 79.99, Genre.FPS, 0, LocalDate.of(2022, 10, 28), true));
        myList.add(new Game("COD Modern Warfare", 34.60, Genre.FPS, 170000, LocalDate.of(2019, 10, 25), true));
        myList.add(new Game("Smite", 0.0, Genre.FIGHTING, 20664, LocalDate.of(2014, 3, 25), true));
        myList.add(new Game("Minecraft", 24.99, Genre.ADVENTURE, 17000000, LocalDate.of(2011, 11, 18), true));
        myList.add(new Game("Phasmophobia", 13.99, Genre.HORROR, 112604, LocalDate.of(2020, 9, 18), true));
        myList.add(new Game("Mario Party Superstars", 49.99, Genre.PARTY_GAME, 3121, LocalDate.of(2021, 10, 29), true));
        myList.add(new Game("Portal 2", 10.0, Genre.PUZZLE, 2587, LocalDate.of(2011, 4, 18), true));
        myList.add(new Game("The Booty Creek Cheek Freak", 0.0, Genre.HORROR, 4400, LocalDate.of(2022, 7, 19), false));
        myList.add(new Game("Elden Ring", 64.98, Genre.ADVENTURE, 150000, LocalDate.of(2022, 2, 25), true));
        myList.add(new Game("Gmod", 9.99, Genre.PUZZLE, 44439, LocalDate.of(2006, 11, 29), true));
        myList.add(new Game("Rainbow Six Siege", 19.99, Genre.FPS, 66141, LocalDate.of(2015, 12, 1), true));
        myList.add(new Game("Friday Night Funkin", 0.0, Genre.PARTY_GAME, 25708, LocalDate.of(2020, 10, 5), false));
        myList.add(new Game("Devil May Cry 5", 9.89, Genre.ADVENTURE, 5418, LocalDate.of(2019, 3, 8), false));
        myList.add(new Game("The Mortuary Assistant", 24.99, Genre.HORROR, 265, LocalDate.of(2022, 8, 2), false));
        // test
        myList.add(new Game("Smite", 100.0, Genre.FIGHTING, 10, LocalDate.of(2020, 2, 12), true));

        return myList;
    }
}