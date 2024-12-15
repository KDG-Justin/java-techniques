package be.kdg.java2.gameproject.persist;

import be.kdg.java2.gameproject.data.Data;
import be.kdg.java2.gameproject.model.Games;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class GamesSerializerTest {
    private Games games;
    private GamesSerializer gamesSerializer;

    @BeforeEach
    void setUp(){
        games = new Games();
        gamesSerializer = new GamesSerializer();
        Data.getData().forEach(games::add);
    }

    @Test
    void testSerialize(){
        try{
            gamesSerializer.serialize(games);
        } catch (IOException e){
            fail();
        }
    }

    @Test
    void testDeserialize(){
        try {
            Games readGames = gamesSerializer.deserialize();
            assertEquals(games.getGames(), readGames.getGames(), "Na deserialize moeten ze gelijk zijn");
        }catch (IOException | ClassNotFoundException e){
            fail();
        }
    }

}