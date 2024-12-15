import be.kdg.java2.gameproject.data.Data;
import be.kdg.java2.gameproject.model.Game;
import be.kdg.java2.gameproject.model.Games;
import be.kdg.java2.gameproject.parsing.GamesDomParser;
import be.kdg.java2.gameproject.parsing.GamesGsonParser;
import be.kdg.java2.gameproject.parsing.GamesJaxbParser;
import be.kdg.java2.gameproject.parsing.GamesStaxParser;
import org.junit.jupiter.api.*;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ParserTest {
    Games games = new Games();

    @BeforeEach
    void setUp(){
        List<Game> myList = Data.getData();
        myList.forEach(games::add);
    }

    @Test
    void testStaxDom(){

        //het zegt, die writer is null in die staxparser klasse idk what to do
        GamesStaxParser parser = new GamesStaxParser(games, "datafiles/staxGames.xml");
        parser.staxWriteXML();
        Games gamesDom = GamesDomParser.domReadXML("datafiles/staxGames.xml");

        assertEquals(games.sortedOnName(), gamesDom.sortedOnName(), "De 2 games objecten zijn niet gelijk");


    }

    @Test
    void testJaxb(){
        // het kent games klasse niet, die marshal in write
        GamesJaxbParser.JaxbWriteXml("datafiles/jaxbGames.xml", games); //marshel
        Games ingelezen = GamesJaxbParser.JaxbReadXml("datafiles/staxGames.xml", Games.class);
        assertEquals(games.sortedOnName(), ingelezen.sortedOnName(), "Ingelezen data komt niet overeen met juiste data");


    }

    @Test
    void testGson(){
        GamesGsonParser.writeJson(games, "datafiles/games.json");
        Games readGames = GamesGsonParser.readJson("datafiles/games.json");

        assertEquals(games.sortedOnName(), readGames.sortedOnName(), "Na het lezen moeten ze gelijk zijn");

    }
}
