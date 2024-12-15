package be.kdg.java2.gameproject.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Array;
import java.time.LocalDate;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class GamesTest {
    private Games games;
    private Game g1;
    private Game g2;
    private Game g3;
    private Game g4;
    private Game g5;

    @BeforeEach
    void setUp(){
        g1 = new Game("Housing", 10.0, Genre.PUZZLE, 1000, LocalDate.of(2019, 2, 20), false);
        g2 = new Game("Civil", 18.0, Genre.FIGHTING, 2000, LocalDate.of(2018, 2, 20), true);
        g3 = new Game("Ant war", 15.0, Genre.FPS, 3000, LocalDate.of(2020, 2, 20), true);
        g4 = new Game("Beer", 13.0, Genre.HORROR, 4000, LocalDate.of(2021, 2, 20), false);
        g5 = new Game("Dead by Daylight", 14.0, Genre.ADVENTURE, 5000, LocalDate.of(2022, 2, 20), true);

        games = new Games();
        games.add(g1);
        games.add(g2);
        games.add(g3);
        games.add(g4);
        games.add(g5);

    }

    @Test
    void testAdd(){

        games.add(new Game("Housing", 10.0, Genre.PUZZLE, 1000, LocalDate.of(2019, 2, 20), false));
        games.add(new Game("Housing", 10.0, Genre.PUZZLE, 1000, LocalDate.of(2019, 2, 20), false));
        assertEquals(5, games.getSize(), "Games worden niet toegevoegd als ze dezelfde naam hebben");

    }

    @Test
    void testRemove(){
        assertEquals(5, games.getSize(), "Er zijn 5 objecten in de verzameling"); // begin situatie
        games.remove("Ant war");
        assertEquals(4, games.getSize(), "Geeft false als er een object niet werd verwijderd");

        games.remove("Ant war"); // object dat niet meer bestaat wegdoen
        assertNotEquals(g3, games.getGames().first(), "Gekozen game bestaat niet in de verzameling");
    }

    @Test
    void testSort(){
        games.sortedBy(Game::getName); // al gesorteerd sinds tree
        assertAll(
                () -> assertEquals(g3, games.getGames().first()),
                () -> assertEquals(g1, games.getGames().last())
        );
    }

    @Test
    void testSortPrice(){
        List<Game> myList = new ArrayList<>();
        myList.add(g1);
        myList.add(g4);
        myList.add(g5);
        myList.add(g3);
        myList.add(g2);

        assertArrayEquals(myList.toArray(), games.sortedBy(Game::getPrice).toArray(), "De Arrays zijn niet met dezelfde conditie gesorteerd");
    }


}