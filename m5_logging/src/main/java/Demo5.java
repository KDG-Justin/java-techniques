import be.kdg.java2.gameproject.data.Data;
import be.kdg.java2.gameproject.model.Game;
import be.kdg.java2.gameproject.model.Games;

import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDate;
import java.util.List;
import java.util.logging.LogManager;

public class Demo5 {
    public static void main(String[] args) {
        loadLoggingConfiguration();
        Game game1 = new Game();
        game1.setPrice(-1);

        Game game2 = new Game();
        game2.setName("");

        Game game3 = new Game();
        game3.setGenre(null);

        Game game4 = new Game();
        game4.setPlayerBase(-1);

        Game game5 = new Game();
        game5.setReleaseDate(null);


        Games games = new Games();
        List<Game> gameList = Data.getData();

        gameList.forEach(games::add);
        System.out.println(games.getSize());

        games.remove("smite");
        games.remove("Gmod");

    }

    private static void loadLoggingConfiguration() {
        // verwijzen naar die loggin.properties bestand
        // op die manier gebruik je dit propertie bestand en niet de oorspronkelijke van java
        InputStream inputStream = Demo5.class.getResourceAsStream("logging.properties");
        try {
            LogManager.getLogManager().readConfiguration(inputStream);
        } catch (IOException e) {
            System.err.println("Logging configuratiebestand kan niet geladen worden");
        }
    }
}
