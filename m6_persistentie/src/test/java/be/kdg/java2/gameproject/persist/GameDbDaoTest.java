package be.kdg.java2.gameproject.persist;

import be.kdg.java2.gameproject.data.Data;
import be.kdg.java2.gameproject.model.Game;
import org.junit.jupiter.api.*;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class GameDbDaoTest {
    private static GameDbDao dao;
    private static List<Game> myList;
    @BeforeAll
    static void setDao(){
        dao = new GameDbDao();
        myList = Data.getData();
    }

    @AfterAll
    static void closeDb(){
        dao.close();
    }

    @BeforeEach
    void fillDb(){
        for (Game game : myList) dao.insert(game);
    }

    @AfterEach
    void deleteDb(){
        dao.deleteAll();
    }


    @Test
    void testInsert(){
        assertEquals(myList, dao.getAllRecords(), "Na insert moeten ze even groot zijn");
    }

    @Test
    void testRetrieveUpdate(){
        Game game1 = dao.retrieve("Smite");
        Game game2 = game1;
        game1.setName("NotSmite");
        dao.update(game1);
        assertEquals(game1, game2, "Na update moeten ze gelijk zijn");
    }

    @Test
    void testDelete(){
        dao.delete("Smite");
        assertEquals(14, dao.getAllRecords().size(), "Na delete is de size 14");
    }

    @Test
    void testSort(){
        List<Game> myList1 = Data.getData();
        myList1.sort(Comparator.comparing(Game::getName));

        List<Game> myList2 = dao.sortedOnName();
        assertEquals(myList1, myList2, "Na sorteren moeten ze gelijk zijn");
    }
}