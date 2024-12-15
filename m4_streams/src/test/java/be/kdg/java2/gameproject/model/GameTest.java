package be.kdg.java2.gameproject.model;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

class GameTest {
    private Game g1;
    private Game g2;

    @BeforeEach
    void setUp() {
        g1 = new Game("Housing", 10.0, Genre.PUZZLE, 1000, LocalDate.of(2019, 2, 20), true);
        g2 = new Game("Housing", 10.0, Genre.FPS, 100000, LocalDate.of(2022, 10, 2), true);

    }

    @Test
    void testEquals(){
        // verander de naam naar dezelfde om het te laten checken
        assertAll(
                () -> assertEquals(g1, g2, "Na vergelijken zou het true moeten geven, want namen zijn hetzelfde"),
                () -> assertEquals(g1.hashCode(), g2.hashCode(), "Games mogen niet dezelfde naam hebben"),
                () -> assertEquals(g1,
                        new Game("Housing", 20, Genre.FIGHTING, 2019, LocalDate.of(2021, 3, 10), false),
                        "Objecten mogen niet dezelfde naam hebben")
        );
    }

    @Test
    void testIllegalPrice(){
        assertThrows(IllegalArgumentException.class, () -> g1.setPrice(-100.09), "negatieve waarde");
    }

    @Test
    void testLegalPrice(){
        assertDoesNotThrow(()-> g1.setPrice(100.2), "De waarde mag niet negatief zijn");
    }

    @Test
    void testCompareTo(){
        //Not veranderen weghalen
        Game g3 = new Game("Housing", 0.0, Genre.FPS, 100000, LocalDate.of(2022, 10, 2), true);
        assertEquals(g1.getName().compareTo(g2.getName()),
                g2.getName().compareTo(g3.getName()), "Geeft true als namen gelijk zijn");
    }

    @Test
    void testPrice(){
        assertEquals(g1.getPrice(), g2.getPrice(), 0.2);
    }
}