package be.kdg.java2.gameproject.persist;

import be.kdg.java2.gameproject.model.Game;
import be.kdg.java2.gameproject.model.Genre;

import java.sql.Date;
import java.util.*;
import java.sql.*;
import java.util.logging.Logger;

public class GameDbDao implements GameDao { // straks implementen met GameDao
    private Connection connection;
    private static final Logger logger = Logger.getLogger("be.kdg.java2.gameproject.persist.GameDbDao");

    public GameDbDao() {
        maakConnectie();
        createTable();
    }

    private void maakConnectie() {
        String databasePath = "db/myDatabase";
        try {
            connection = DriverManager.getConnection("jdbc:hsqldb:file:" + databasePath, "sa", "");
            System.out.println("Connection gemaakt");
        } catch (SQLException e) {
            logger.severe("Kan geen connectie maken met database " + databasePath);
            System.exit(1);
        }
    }

    private void createTable() {
        try {
            Statement statement = connection.createStatement();
            statement.execute("DROP TABLE IF EXISTS gamesdb");
            String createQuery = "CREATE TABLE gamesdb " +
                    "(id INTEGER NOT NULL IDENTITY," +
                    "name VARCHAR(30) NOT NULL, " +
                    "price DOUBLE," +
                    "genre VARCHAR(30) NOT NULL," +
                    "playerbase INTEGER," +
                    "release DATE NOT NULL," +
                    "multiplayer BIT)";
            statement.execute(createQuery);
            System.out.println("Database aangemaakt");
        } catch (SQLException e) {
            logger.severe("Onverwachte fout bij aanmaken tabel: " + e.getMessage());
            System.exit(1);
        }
    }

    public void close() {
        if (connection == null) return;
        try {
            Statement statement = connection.createStatement();
            statement.execute("SHUTDOWN COMPACT");
            statement.close();
            connection.close();
            System.out.println("\nDatabase gesloten");
        } catch (SQLException e) {
            logger.severe("Probleem bij sluiten van database: " + e.getMessage());
        }
    }


    @Override
    public boolean insert(Game game) {
        if (game.getId() >= 0) return false;
        try {
            PreparedStatement statement = connection.prepareStatement(
                    "INSERT INTO gamesdb VALUES(NULL, ?, ?, ?, ?, ?, ?)"
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
            return result;

        } catch (SQLException e) {
            logger.severe("Fout bij create: " + e);
            return false;
        }
    }

    @Override
    public Game retrieve(String gekozenNaam) {
        List<Game> gameList = new ArrayList<>();
        Game game = null;
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM gamesdb WHERE name ='" + gekozenNaam + "'");
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
            return game;
        } catch (SQLException e) {
            logger.severe("Fout bij retrieve: " + e);
            return null;
        }
    }

    public List<Game> getAllRecords() {
        List<Game> myList = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM gamesdb");
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
        } catch (SQLException e) {
            System.err.println("Fout bij alles retrieven: " + e);
        }
        return myList;
    }

    @Override
    public boolean delete(String gekozenNaam) {
        try {
            Statement statement = connection.createStatement();
            int rowAffected = statement.executeUpdate("DELETE FROM gamesdb WHERE name ='" + gekozenNaam + "'");
            statement.close();
            return (rowAffected == 1);

        } catch (SQLException e) {
            logger.severe("Fout bij delete: " + e);
            return false;
        }
    }

    public boolean deleteAll() {
        try {
            Statement statement = connection.createStatement();
            int rowsAffected = statement.executeUpdate("DELETE FROM gamesdb");
            statement.close();
            return (rowsAffected == 1);
        } catch (SQLException e) {
            System.err.println("Fout bij delete alles: " + e);
            return false;
        }
    }

    @Override
    public boolean update(Game game) {
        try {

            PreparedStatement prepareStatement = connection.prepareStatement(
                    "UPDATE gamesdb SET name = ?, price = ?, genre = ?, playerbase = ?, release = ?, multiplayer = ?" +
                            " WHERE id ='" + game.getId() + "' ");
            prepareStatement.setString(1, game.getName());
            prepareStatement.setDouble(2, game.getPrice());
            prepareStatement.setString(3, game.getGenre().name());
            prepareStatement.setInt(4, game.getPlayerBase());
            prepareStatement.setDate(5, Date.valueOf(game.getReleaseDate()));
            prepareStatement.setBoolean(6, game.isMultiplayer());
            prepareStatement.executeUpdate();


            return true;
        } catch (SQLException e) {
            logger.severe("Fout bij update: " + e);
            return false;
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
            return gameList;

        } catch (SQLException e) {
            logger.severe("Fout bij sorteren: " + e);
            return gameList;
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
