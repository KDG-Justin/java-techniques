package be.kdg.java2.gameproject.persist;

import be.kdg.java2.gameproject.data.Data;
import be.kdg.java2.gameproject.model.Game;
import be.kdg.java2.gameproject.model.Games;

import java.io.*;
import java.util.TreeSet;

public class GamesSerializer {
    public final String FILENAME;

    public GamesSerializer() {
        this.FILENAME = "db/games.ser";
    }

    public void serialize(Games games) throws IOException {
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("db/games.ser"));
        oos.writeObject(games.getGames());
        oos.close();
    }

    public Games deserialize() throws IOException, ClassNotFoundException{
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream("db/games.ser"));
        Games games = new Games();
        TreeSet<Game> gameTreeSet = (TreeSet) ois.readObject();
        gameTreeSet.forEach(games::add);
        return games;
    }


}
