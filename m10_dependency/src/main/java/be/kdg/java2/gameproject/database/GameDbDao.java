package be.kdg.java2.gameproject.database;

import be.kdg.java2.gameproject.data.Data;
import be.kdg.java2.gameproject.exceptions.GameException;
import be.kdg.java2.gameproject.model.Game;
import be.kdg.java2.gameproject.model.Genre;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class GameDbDao implements GameDao {
    private Connection connection;

    private static GameDbDao instance;
    private static final Logger logger = Logger.getLogger("be.kdg.java2.gameproject.database.GameDbDao");

    public static GameDbDao getInstance() throws SQLException {
        if (instance == null) {
            instance = new GameDbDao();
        }
        return instance;
    }

    GameDbDao() {
        maakConnectie();
        createTable();
    }

    private void maakConnectie() {
        String databasePath = "db/myDb";
        try {
            connection = DriverManager.getConnection("jdbc:hsqldb:file:" + databasePath, "sa", "");
            logger.info("Connectie gemaakt");
        } catch (SQLException e) {
            logger.warning("Kan geen connectie maken met database " + databasePath);
            throw new GameException(e);
        }
    }

    private void createTable(){
        try(Statement statement = connection.createStatement()) {
            DatabaseMetaData dbm = connection.getMetaData();
            ResultSet tables = dbm.getTables(null, null, "GAMES", null);
            if (!tables.next()){
                statement.execute("DROP TABLE IF EXISTS GAMES");
                statement.executeUpdate("CREATE TABLE GAMES " +
                        "(id INTEGER NOT NULL IDENTITY," +
                        "name VARCHAR(30) NOT NULL, " +
                        "price DOUBLE," +
                        "genre VARCHAR(30) NOT NULL," +
                        "playerbase INTEGER," +
                        "release DATE NOT NULL," +
                        "multiplayer BIT)");
            }
            for (Game game : Data.getData()){
                insert(game);
            }
            logger.info("Tabel gemaakt en gevuld");
        }catch (SQLException e){
            logger.warning("Onverwachte fout bij aanmaken tabel: " + e.getMessage());
            throw new GameException(e);
        }
    }

    public void close() {
        if (connection == null) return;
        try {
            Statement statement = connection.createStatement();
            statement.execute("SHUTDOWN COMPACT");
            statement.close();
            connection.close();
            logger.info("Databank gesloten");
        } catch (SQLException e) {
            logger.warning("Probleem bij sluiten van database: " + e.getMessage());
            throw new GameException(e);
        }
    }


    @Override
    public boolean insert(Game game) {
        //if (game.getId() >= 0) return false;
        try {
            PreparedStatement statement = connection.prepareStatement(
                    "INSERT INTO GAMES VALUES(NULL, ?, ?, ?, ?, ?, ?)"
            );
            statement.setString(1, game.getName());
            statement.setDouble(2, game.getPrice());
            statement.setString(3, game.getGenre().name());
            statement.setInt(4, game.getPlayerBase());
            statement.setDate(5, Date.valueOf(game.getReleaseDate()));
            statement.setBoolean(6, game.isMultiplayer());

            int rowsAffected = statement.executeUpdate();
            boolean result = rowsAffected == 1;
            statement.close();
            logger.info("Game succcesvol aangemaakt");
            return result;
        } catch (SQLException e) {
            logger.warning("Fout bij create: " + e);
            throw new GameException(e);
        }
    }

    @Override
    public Game retrieve(String gekozenNaam) {
        //List<Game> gameList = new ArrayList<>();
        Game game = null;
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM GAMES WHERE name ='" + gekozenNaam + "'");
            while (rs.next()) {
                 game = new Game(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getDouble("price"),
                        Genre.valueOf(rs.getString("genre")),
                        rs.getInt("playerbase"),
                        rs.getDate("release").toLocalDate(),
                        rs.getBoolean("multiplayer")
                );
            }
            logger.info("Game gevonden");
            return game;
        } catch (SQLException e) {
            logger.warning("Fout bij retrieve: " + e);
            throw new GameException(e);
        }
    }

    public List<Game> getAllRecords() {
        List<Game> myList = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM GAMES");
            while (rs.next()) {
                myList.add(new Game(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getDouble("price"),
                        Genre.valueOf(rs.getString("genre")),
                        rs.getInt("playerbase"),
                        rs.getDate("release").toLocalDate(),
                        rs.getBoolean("multiplayer")
                ));

            }
            logger.info("Succesvol alles opgehaald");
        } catch (SQLException e) {
            logger.warning("Fout bij alles retrieven: " + e);
            throw new GameException(e);
        }
        return myList;
    }

    @Override
    public boolean delete(String gekozenNaam) {
        try {
            Statement statement = connection.createStatement();
            int rowAffected = statement.executeUpdate("DELETE FROM GAMES WHERE name ='" + gekozenNaam + "'");
            statement.close();
            logger.info("Succesvol een game verwijderd");
            return (rowAffected == 1);
        } catch (SQLException e) {
            logger.warning("Fout bij delete: " + e);
            throw new GameException(e);
        }
    }

    public boolean deleteAll() {
        try {
            Statement statement = connection.createStatement();
            int rowsAffected = statement.executeUpdate("DELETE FROM GAMES");
            statement.close();
            logger.info("succesvol alles verwijderd");
            return (rowsAffected == 1);
        } catch (SQLException e) {
            logger.warning("Fout bij delete alles: " + e);
            throw new GameException(e);
        }
    }

    @Override
    public boolean update(Game game) {
        try {

            PreparedStatement prepareStatement = connection.prepareStatement(
                    "UPDATE GAMES SET name = ?, price = ?, genre = ?, playerbase = ?, release = ?, multiplayer = ?" +
                            " WHERE id ='" + game.getId() + "' ");
            prepareStatement.setString(1, game.getName());
            prepareStatement.setDouble(2, game.getPrice());
            prepareStatement.setString(3, game.getGenre().name());
            prepareStatement.setInt(4, game.getPlayerBase());
            prepareStatement.setDate(5, Date.valueOf(game.getReleaseDate()));
            prepareStatement.setBoolean(6, game.isMultiplayer());
            prepareStatement.executeUpdate();

            logger.info("Game bijgewerkt");
            return true;
        } catch (SQLException e) {
            logger.warning("Fout bij update: " + e);
            throw new GameException(e);
        }
    }

    @Override
    public List<Game> sortedOn(String query) {
        List<Game> gameList = new ArrayList<>();

        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(query);
            while (rs.next()) {
                gameList.add(new Game(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getDouble("price"),
                        Genre.valueOf(rs.getString("genre")),
                        rs.getInt("playerbase"),
                        rs.getDate("release").toLocalDate(),
                        rs.getBoolean("multiplayer")
                ));
            }
            logger.info("Databank gesorteerd");
            return gameList;
        } catch (SQLException e) {
            logger.warning("Fout bij sorteren: " + e);
            throw new GameException(e);
        }
    }

    @Override
    public List<Game> getAllGames() {
        List<Game> myList = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM GAMES");
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                myList.add(new Game(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getDouble("price"),
                        Genre.valueOf(rs.getString("genre")),
                        rs.getInt("playerbase"),
                        rs.getDate("release").toLocalDate(),
                        rs.getBoolean("multiplayer")
                ));

            }
            preparedStatement.close();
            logger.warning("Alle games zijn gevonden");
            return myList;
        } catch (SQLException e) {
            logger.warning("Fout bij alles retrieven: " + e);
            throw new GameException(e);
        }
    }

    public List<Game> sortedOnName() {
        String query = "SELECT * FROM gamesdb ORDER BY name";
        return sortedOn(query);
    }

    public List<Game> sortedOnPrice() {
        String query = "SELECT * FROM gamesdb ORDER BY price";
        return sortedOn(query);
    }

}
