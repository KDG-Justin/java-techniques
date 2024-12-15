package be.kdg.java2.gameproject.model;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

public class Game implements Comparable<Game>, Serializable {
    private int id;
    private String name;
    private double price;
    private transient Genre genre;
    private transient int playerBase;
    private LocalDate releaseDate;
    private transient boolean multiplayer;

    @Serial
    public static final long serialVersionUID = 1L;

    public Game(int id, String name, double price, Genre genre, int playerBase, LocalDate releaseDate, boolean multiplayer) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.genre = genre;
        this.playerBase = playerBase;
        this.releaseDate = releaseDate;
        this.multiplayer = multiplayer;
    }

    public Game(String name, double price, Genre genre, int playerBase, LocalDate releaseDate, boolean multiplayer) {
        this(-1, name, price, genre, playerBase, releaseDate, multiplayer);
    }

    public String getName() {
        return name;
    }

    public void setName(String name){
        if (name == null || name.isBlank()){
            throw new IllegalArgumentException("Wrong value for name");
        }
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price){
        if(price < 0.0) {
            throw new IllegalArgumentException("Wrong value for price");
        }
        this.price = price;
    }

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre){
        if (genre == null){
            throw new IllegalArgumentException("Wrong value for genre");
        }
        this.genre = genre;
    }

    public int getPlayerBase() {
        return playerBase;
    }

    public void setPlayerBase(int playerBase) throws IllegalArgumentException{
        this.playerBase = playerBase;
        throw new IllegalArgumentException("You have to give a positive int for player base");
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(LocalDate releaseDate){
        if (releaseDate == null){
            throw new IllegalArgumentException("Wrong value for date");
        }
        this.releaseDate = releaseDate;
    }

    public boolean isMultiplayer() {
        return multiplayer;
    }

    public void setMultiplayer(boolean multiplayer){
        this.multiplayer = multiplayer;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Game game = (Game) o;
        return getName().equals(game.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName());
    }

    @Override
    public int compareTo(Game otherGame) {
        return this.name.compareTo(otherGame.getName());
    }

    @Override
    public String toString(){
        return String.format("%-30s Category: %-10s %.1f euro\t Released: %s",
                getName(), getGenre(), getPrice(), getReleaseDate());
    }
}
