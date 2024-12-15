package be.kdg.java2.gameproject.model;

import java.time.LocalDate;
import java.util.Objects;

public final class Game implements Comparable<Game>{
    private final String name;
    private final double price;
    private final Genre genre;
    private final int playerBase;
    private final LocalDate releaseDate;
    private final boolean multiplayer;


    public Game(String name, double price, Genre genre, int playerBase, LocalDate releaseDate, boolean multiplayer) {
        this.name = name;
        this.price = price;
        this.genre = genre;
        this.playerBase = playerBase;
        this.releaseDate = releaseDate;
        this.multiplayer = multiplayer;
    }


    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public Genre getGenre() {
        return genre;
    }

    public int getPlayerBase() {
        return playerBase;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public boolean isMultiplayer() {
        return multiplayer;
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
